package com.win.dfas.monitor.engine.websocket;


import com.win.dfas.monitor.common.constant.HomeModuleEnum;

import java.io.IOException;

public abstract class AbstractWebSocket {

    public abstract HomeModuleEnum getModuleName();

    public abstract void sendMessage(String message) throws IOException;

}
