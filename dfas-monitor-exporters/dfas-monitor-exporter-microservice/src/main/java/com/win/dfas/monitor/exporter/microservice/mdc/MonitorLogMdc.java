package com.win.dfas.monitor.exporter.microservice.mdc;

import com.win.dfas.monitor.common.util.NetUtils;
import org.slf4j.MDC;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.SocketException;

@Component
public class MonitorLogMdc implements ApplicationListener<WebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        int serverPort = event.getWebServer().getPort();
        MDC.put("port", String.valueOf(serverPort));
    }

    @PostConstruct
    public void init() {
        try {
            MDC.put("ip", NetUtils.getLocalIpAddress());
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}
