package com.win.dfas.monitor.engine.websocket;

class WebSocketManager extends AbstractWebSocketManager {

    @Override
    public void add(AbstractWebSocket socket) {
        webSocketSet.add(socket);
    }

    @Override
    public void remove(AbstractWebSocket socket) {
        webSocketSet.remove(socket);
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
