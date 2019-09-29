package com.win.dfas.monitor.engine.websocket;

import java.io.IOException;

public abstract class AbstractWebSocket {


    public abstract  void sendMessage(String message) throws IOException;

}
