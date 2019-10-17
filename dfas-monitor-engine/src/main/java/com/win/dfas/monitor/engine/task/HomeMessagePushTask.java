package com.win.dfas.monitor.engine.task;


import com.win.dfas.monitor.common.constant.HomeModuleEnum;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocket;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocketManager;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 发送通知消息线程
 * 包名称：com.win.dfas.monitor.engine.task;
 * 类名称：HomeMessagePush
 * 类描述：负责将监控数据推送到大屏
 * 创建人：@author wangyaoheng
 * 创建时间：2019-08-23/9:00
 */
public class HomeMessagePushTask extends AbstractMessageBuilder implements Runnable {

    @Override
    public void run() {
        CopyOnWriteArraySet<AbstractWebSocket> webSocketSet = AbstractWebSocketManager.instance().get();
        System.out.println("客户端连接个数：" + webSocketSet.size());
        for (AbstractWebSocket webSocket : webSocketSet) {
            try {
                if (HomeModuleEnum.platformOverview == webSocket.getModuleName()) {
                    webSocket.sendMessage(getPlatformOverviewData());
                } else if (HomeModuleEnum.qps == webSocket.getModuleName()) {
                    webSocket.sendMessage(getQpsData());
                } /*else if (HomeModuleEnum.machineState == webSocket.getModuleName()) {
                    webSocket.sendMessage(getMachineStatusData());
                }*/ else if (HomeModuleEnum.microServiceState == webSocket.getModuleName()) {
                    webSocket.sendMessage(getMicroServiceStatusData());
                } /*else if (HomeModuleEnum.machineList == webSocket.getModuleName()) {
                    webSocket.sendMessage(getMachineListData());
                }*/ else if (HomeModuleEnum.exception == webSocket.getModuleName()) {
                    webSocket.sendMessage(getExceptionData());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
