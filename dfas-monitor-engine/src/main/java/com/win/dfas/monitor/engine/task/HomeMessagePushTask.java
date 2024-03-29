package com.win.dfas.monitor.engine.task;


import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

import com.win.dfas.monitor.common.constant.LogLevelEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.win.dfas.common.util.ObjectUtils;
import com.win.dfas.monitor.common.constant.HomeModuleEnum;
import com.win.dfas.monitor.common.constant.MonitorConstants;
import com.win.dfas.monitor.common.constant.StatusEnum;
import com.win.dfas.monitor.common.dto.microservice.ApplicationInstance;
import com.win.dfas.monitor.common.entity.MicroServiceEntity;
import com.win.dfas.monitor.common.entity.MicroServiceInstanceEntity;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.vo.ExceptionVO;
import com.win.dfas.monitor.common.vo.MicroServiceRepVO;
import com.win.dfas.monitor.common.vo.PlatformOverviewVO;
import com.win.dfas.monitor.config.mapper.MicroServiceInstanceMapper;
import com.win.dfas.monitor.config.mapper.MicroServiceMapper;
import com.win.dfas.monitor.engine.service.ElasticsearchService;
import com.win.dfas.monitor.engine.service.EurekaService;
import com.win.dfas.monitor.engine.service.IMachineService;
import com.win.dfas.monitor.engine.service.PrometheusService;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocket;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocketManager;

/**
 * 发送通知消息线程
 * 包名称：com.win.dfas.monitor.engine.task;
 * 类名称：HomeMessagePush
 * 类描述：负责将监控数据推送到大屏
 * 创建人：@author wangyaoheng
 * 创建时间：2019-08-23/9:00
 */

//@Component
public class HomeMessagePushTask extends AbstractMessageBuilder {

    static Logger log = LoggerFactory.getLogger(HomeMessagePushTask.class);
	
    @Autowired
    private EurekaService eurekaService;

    @Autowired
    private IMachineService dcDevcieService;

    @Autowired
    private MicroServiceMapper microServiceMapper;

    @Autowired
    private MicroServiceInstanceMapper microServiceInstanceMapper;

    @Autowired
    private PrometheusService prometheusService;
    
    @Autowired
    private ElasticsearchService elasticsearchService;

    /**
     * 按照标准时间来算，每隔 5s 执行一次
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void pushPlatformOverviewData() {
    	log.warn("pushPlatformOverviewData");
        Random random = new Random(System.currentTimeMillis());
        PlatformOverviewVO platformOverview = new PlatformOverviewVO();
        platformOverview.setQps(String.valueOf(thousandBitNumberFormat.format(prometheusService.getQps())));
        platformOverview.setTotalHttpRequest(String.valueOf(thousandBitNumberFormat.format(prometheusService.getHttpRequestTotal())));
        platformOverview.setTotalMicroService(String.valueOf(thousandBitNumberFormat.format(microServiceMapper.getTotalMicroService())));
        platformOverview.setTotalNode(String.valueOf(thousandBitNumberFormat.format(dcDevcieService.getTotalNode())));
        push(HomeModuleEnum.platformOverview, JsonUtil.toJson(platformOverview));
    }

    @Scheduled(cron = "0/6 * * * * ?")
    public void pushQpsData() {
        push(HomeModuleEnum.qps, JsonUtil.toJson(prometheusService.getQpsChart()));
    }

    @Scheduled(cron = "0/7 * * * * ?")
    public void pushMicroServiceStateData() {
    	
        List<MicroServiceEntity> microServiceEntityList = microServiceMapper.selectMicroServiceList(null);
        List<MicroServiceRepVO> microServiceRepList = ObjectUtils.copyPropertiesList(microServiceEntityList, MicroServiceRepVO.class);
        Map<String, ApplicationInstance> microServiceInstanceMap = eurekaService.fetchMicroService();
        for (MicroServiceRepVO microServiceRepVO : microServiceRepList) {
            List<MicroServiceInstanceEntity> instanceList = microServiceInstanceMapper.selectMicroServiceInstanceListByServiceId(microServiceRepVO.getId());
            int upCount = 0;
            if (instanceList != null) {
                for (MicroServiceInstanceEntity microServiceInstanceEntity : instanceList) {
                    ApplicationInstance applicationInstance = microServiceInstanceMap.get(microServiceInstanceEntity.getIpAddr());
                    if (applicationInstance != null) {
                        if (MonitorConstants.UP_STATUS.equals(applicationInstance.getStatus())) {
                            upCount++;
                        }
                    }
                }
            }

            if(instanceList == null  ||  upCount ==0) {
                microServiceRepVO.setState(StatusEnum.OFFLINE.getStatus());
            }else if(upCount  < instanceList.size()){
                microServiceRepVO.setState(StatusEnum.EXCEPTION.getStatus());
            }else if(upCount ==instanceList.size()){
                microServiceRepVO.setState(StatusEnum.ONLINE.getStatus());
                //继续判断JVM内存是否存在告警，存在，则设置为告警状态
            }

            try {
                Map<String, Long> bucketMap = elasticsearchService.getLogTotalCountByMicroService(microServiceRepVO.getMicroServiceName());
                long warnCount = 0;
                if (bucketMap.get(LogLevelEnum.WARN.name()) != null) {
                    warnCount = bucketMap.get(LogLevelEnum.WARN.name());
                }
                long errorCount = 0;
                if (bucketMap.get(LogLevelEnum.ERROR.name()) != null) {
                    errorCount = bucketMap.get(LogLevelEnum.ERROR.name());
                }
                microServiceRepVO.setWarn(String.valueOf(thousandBitNumberFormat.format(warnCount)));
                microServiceRepVO.setError(String.valueOf(thousandBitNumberFormat.format(errorCount)));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        CopyOnWriteArraySet<AbstractWebSocket> webSocketSet = AbstractWebSocketManager.instance().get(HomeModuleEnum.microServiceState);
        if (webSocketSet != null) {
            System.out.println("客户端连接个数：" + webSocketSet.size());
            for (AbstractWebSocket webSocket : webSocketSet) {
                try {
                    webSocket.sendMessage(JsonUtil.toJson(microServiceRepList));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Scheduled(cron = "0/8 * * * * ?")
    public void pushExceptionData() throws Exception {
        Map<String,Long> bucketMap=elasticsearchService.getLogTotalCount();
    	Random random = new Random(System.currentTimeMillis());
        ExceptionVO exception = new ExceptionVO();
        exception.setMachineCpu(String.valueOf(thousandBitNumberFormat.format(random.nextInt(100))));
        exception.setMachineMemory(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10000))));
        exception.setMachineDisk(String.valueOf(thousandBitNumberFormat.format(random.nextInt(1000))));
        exception.setMicroServiceMemory(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10))));
       long warnCount=0;
        if(bucketMap.get("WARN") != null){
            warnCount = bucketMap.get("WARN");
        }
        long errorCount=0;
        if(bucketMap.get("ERROR") != null){
            errorCount = bucketMap.get("ERROR");
        }
        exception.setMicroServiceWarnLog(String.valueOf(thousandBitNumberFormat.format(warnCount)));
        exception.setMicroServiceErrorLog(String.valueOf(thousandBitNumberFormat.format(errorCount)));
    	
        CopyOnWriteArraySet<AbstractWebSocket> webSocketSet = AbstractWebSocketManager.instance().get(HomeModuleEnum.exception);
        if (webSocketSet != null) {
            System.out.println("客户端连接个数：" + webSocketSet.size());
            for (AbstractWebSocket webSocket : webSocketSet) {
                try {
                    webSocket.sendMessage(JsonUtil.toJson(exception));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        

        
        
    }



}
