package com.win.dfas.monitor.engine.task;


import com.win.dfas.common.util.ObjectUtils;
import com.win.dfas.monitor.common.constant.HomeModuleEnum;
import com.win.dfas.monitor.common.dto.microservice.ApplicationInstance;
import com.win.dfas.monitor.common.entity.MicroServiceEntity;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.vo.MicroServiceMachineRepVO;
import com.win.dfas.monitor.common.vo.MicroServiceReqVO;
import com.win.dfas.monitor.config.mapper.MicroServiceMapper;
import com.win.dfas.monitor.engine.service.EurekaService;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocket;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocketManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
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
    private MicroServiceMapper microServiceMapper;

    public void push(HomeModuleEnum homeModuleEnum) {
        CopyOnWriteArraySet<AbstractWebSocket> webSocketSet = AbstractWebSocketManager.instance().get(homeModuleEnum);
        if (webSocketSet != null) {
            System.out.println("客户端连接个数：" + webSocketSet.size());
            for (AbstractWebSocket webSocket : webSocketSet) {
                try {
                    if (HomeModuleEnum.platformOverview == homeModuleEnum) {
                        webSocket.sendMessage(getPlatformOverviewData());
                    } else if (HomeModuleEnum.qps == homeModuleEnum) {
                        webSocket.sendMessage(getQpsData());
                    } else if (HomeModuleEnum.microServiceState == homeModuleEnum) {
                        webSocket.sendMessage(getMicroServiceStatusData());
                    } else if (HomeModuleEnum.exception == homeModuleEnum) {
                        webSocket.sendMessage(getExceptionData());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 按照标准时间来算，每隔 5s 执行一次
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void pushPlatformOverviewData() throws Exception {
        push(HomeModuleEnum.platformOverview);
    }

    @Scheduled(cron = "0/6 * * * * ?")
    public void pushQpsData() throws Exception {
        push(HomeModuleEnum.qps);
    }

    @Scheduled(cron = "0/7 * * * * ?")
    public void pushMicroServiceStateData() {
        List<MicroServiceEntity> list = microServiceMapper.selectMicroServiceList(null);
        List<MicroServiceReqVO> microServiceReqList = ObjectUtils.copyPropertiesList(list, MicroServiceReqVO.class);
        Map<String, ApplicationInstance> microServiceMap = eurekaService.fetchMicroService();
        for (MicroServiceReqVO microServiceReqVO : microServiceReqList) {
            /*ApplicationInstance applicationInstance = microServiceMap.get(microServiceMachineRep.getInstanceId());
            if (applicationInstance != null) {
                microServiceReqVO.setState(applicationInstance.getStatus());
            }*/
        }
        CopyOnWriteArraySet<AbstractWebSocket> webSocketSet = AbstractWebSocketManager.instance().get(HomeModuleEnum.microServiceState);
        if (webSocketSet != null) {
            System.out.println("客户端连接个数：" + webSocketSet.size());
            for (AbstractWebSocket webSocket : webSocketSet) {
                try {
                    webSocket.sendMessage(JsonUtil.toJson(microServiceReqList));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Scheduled(cron = "0/8 * * * * ?")
    public void pushExceptionData() throws Exception {
        push(HomeModuleEnum.exception);
    }

}
