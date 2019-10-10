package com.win.dfas.monitor.web.websocket;

import com.win.dfas.monitor.common.constant.HomeModuleEnum;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocket;
import com.win.dfas.monitor.engine.websocket.AbstractWebSocketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 包名称：com.win.dfas.monitor.web.websocket
 * 类名称：HomeWebSocketEndpoint
 * 类描述：监控平台主控面板
 * 创建人：@author wangyaoheng
 * 创建时间：2019-09-27/13:31
 */
@ServerEndpoint("/home/{moduleName}")
@Component
public class HomeWebSocketEndpoint extends AbstractWebSocket {

    static Logger log = LoggerFactory.getLogger(HomeWebSocketEndpoint.class);

    /** 与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /** 模块名 */
    private HomeModuleEnum moduleName;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("moduleName") String moduleName) {
        System.out.println("session open");
        this.session = session;
        this.moduleName = HomeModuleEnum.valueOf(moduleName);
        AbstractWebSocketManager.instance().add(this);
    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        log.info("client[moduleName=" + moduleName + "][sessionid=" + session.getId() + "] 关闭连接 !");
        //从set中删除
        AbstractWebSocketManager.instance().remove(this);
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("receive message : " + message + " from client [moduleName=" + moduleName + "]");
    }


    /**
     * 错误处理
     * @param session
     * @param e
     */
    @OnError
    public void onError(Session session, Throwable e) {
        log.error(e.getMessage());
    }

    @Override
    public HomeModuleEnum getModuleName() {
        return moduleName;
    }

    /**
     * 实现服务器主动推送
     */
    @Override
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
