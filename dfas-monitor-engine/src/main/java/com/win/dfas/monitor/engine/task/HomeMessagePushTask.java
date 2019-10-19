package com.win.dfas.monitor.engine.task;


import com.win.dfas.common.util.ObjectUtils;
import com.win.dfas.monitor.common.constant.HomeModuleEnum;
import com.win.dfas.monitor.common.constant.MonitorConstants;
import com.win.dfas.monitor.common.dto.microservice.ApplicationInstance;
import com.win.dfas.monitor.common.entity.MicroServiceEntity;
import com.win.dfas.monitor.common.entity.MicroServiceInstanceEntity;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.vo.MicroServiceRepVO;
import com.win.dfas.monitor.common.vo.PlatformOverviewVO;
import com.win.dfas.monitor.config.mapper.MicroServiceInstanceMapper;
import com.win.dfas.monitor.config.mapper.MicroServiceMapper;
import com.win.dfas.monitor.engine.service.EurekaService;
import com.win.dfas.monitor.engine.service.IDcDevcieService;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocket;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocketManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 发送通知消息线程
 * 包名称：com.win.dfas.monitor.engine.task;
 * 类名称：HomeMessagePush
 * 类描述：负责将监控数据推送到大屏
 * 创建人：@author wangyaoheng
 * 创建时间：2019-08-23/9:00
 */
@Slf4j
@Component
public class HomeMessagePushTask extends AbstractMessageBuilder {

    @Autowired
    private EurekaService eurekaService;

    @Autowired
    private IDcDevcieService dcDevcieService;

    @Autowired
    private MicroServiceMapper microServiceMapper;

    @Autowired
    private MicroServiceInstanceMapper microServiceInstanceMapper;


    /**
     * 按照标准时间来算，每隔 5s 执行一次
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void pushPlatformOverviewData() {
        Random random = new Random(System.currentTimeMillis());
        PlatformOverviewVO platformOverview = new PlatformOverviewVO();
        platformOverview.setQps(String.valueOf(thousandBitNumberFormat.format(random.nextInt(100) + 1)));
        platformOverview.setTotalHttpRequest(String.valueOf(thousandBitNumberFormat.format(random.nextInt(10000) + 1)));
        platformOverview.setTotalMicroService(String.valueOf(thousandBitNumberFormat.format(microServiceMapper.getTotalMicroService())));
        platformOverview.setTotalNode(String.valueOf(thousandBitNumberFormat.format(dcDevcieService.getTotalNode())));
        push(HomeModuleEnum.platformOverview, JsonUtil.toJson(platformOverview));
    }

    @Scheduled(cron = "0/6 * * * * ?")
    public void pushQpsData() throws Exception {
        push(HomeModuleEnum.qps, getQpsData());
    }

    @Scheduled(cron = "0/7 * * * * ?")
    public void pushMicroServiceStateData() {
        List<MicroServiceEntity> microServiceEntityList = microServiceMapper.selectMicroServiceList(null);
        List<MicroServiceRepVO> microServiceRepList = ObjectUtils.copyPropertiesList(microServiceEntityList, MicroServiceRepVO.class);
        Map<String, ApplicationInstance> microServiceInstanceMap = eurekaService.fetchMicroService();
        Random random = new Random(System.currentTimeMillis());
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

            microServiceRepVO.setState(String.valueOf(random.nextInt(4)));
            /*if(instanceList == null  ||  upCount ==0) {
                microServiceRepVO.setState(StatusEnum.OFFLINE.getStatus());
            }else if(upCount  < instanceList.size()){
                microServiceRepVO.setState(StatusEnum.EXCEPTION.getStatus());
            }else if(upCount ==instanceList.size()){
                microServiceRepVO.setState(StatusEnum.ONLINE.getStatus());
                //继续判断JVM内存是否存在告警，存在，则设置为告警状态
            }*/
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
        push(HomeModuleEnum.exception, getExceptionData());
    }

}
