package com.win.dfas.monitor.engine.websocket;

import java.util.concurrent.CopyOnWriteArraySet;

public abstract class AbstractWebSocketManager {

    /**
     * 获取查询实例
     * @return 实例对象
     */
    public static AbstractWebSocketManager instance() {
        return WebSocketManager.getInstance();
    }

    /** concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。*/
    protected static CopyOnWriteArraySet<AbstractWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    public abstract void add(AbstractWebSocket socket);

    public abstract void remove(AbstractWebSocket socket);

    public abstract  CopyOnWriteArraySet<AbstractWebSocket> get();
}
