package com.win.dfas.monitor.engine.websocket;

import com.win.dfas.monitor.common.constant.HomeModuleEnum;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
    protected static Map<HomeModuleEnum, CopyOnWriteArraySet<AbstractWebSocket>> webSocketMap = new ConcurrentHashMap<>();

    public abstract void add(AbstractWebSocket socket);

    public abstract void remove(AbstractWebSocket socket);

    public abstract CopyOnWriteArraySet<AbstractWebSocket> get(HomeModuleEnum homeModuleEnum);
}
