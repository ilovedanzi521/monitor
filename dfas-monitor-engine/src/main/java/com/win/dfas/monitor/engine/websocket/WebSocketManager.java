package com.win.dfas.monitor.engine.websocket;

import com.win.dfas.monitor.common.constant.HomeModuleEnum;

import java.util.concurrent.CopyOnWriteArraySet;

class WebSocketManager extends AbstractWebSocketManager {

    @Override
    public void add(AbstractWebSocket socket) {
        CopyOnWriteArraySet<AbstractWebSocket> webSocketSet = webSocketMap.get(socket.getModuleName());
        if (webSocketSet == null) {
            webSocketSet = new CopyOnWriteArraySet<>();
            webSocketMap.put(socket.getModuleName(), webSocketSet);
        }
        webSocketSet.add(socket);
    }

    @Override
    public void remove(AbstractWebSocket socket) {
        CopyOnWriteArraySet<AbstractWebSocket> webSocketSet = webSocketMap.get(socket.getModuleName());
        if (webSocketSet != null) {
            webSocketSet.remove(socket);
        }
    }

    @Override
    public CopyOnWriteArraySet<AbstractWebSocket> get(HomeModuleEnum homeModuleEnum) {
        return webSocketMap.get(homeModuleEnum);
    }

    /**
     * 单例
     * @author wangyh
     */
    private static class SingletonHolder {
        public final static AbstractWebSocketManager instance = new WebSocketManager();
    }

    /**
     * 获取单例
     * @return
     */
    public static AbstractWebSocketManager getInstance() {
        return SingletonHolder.instance;
    }


}
