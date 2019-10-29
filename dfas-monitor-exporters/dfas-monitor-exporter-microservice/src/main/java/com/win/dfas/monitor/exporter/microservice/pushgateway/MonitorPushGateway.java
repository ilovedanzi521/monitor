package com.win.dfas.monitor.exporter.microservice.pushgateway;


import io.prometheus.client.exporter.PushGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MonitorPushGateway {


    @Value("${pushgateway.server.url}")
    private String pushgatewayServerUrl;

    @Bean
    public PushGateway getPushGateway() {
        PushGateway pushGateway = new PushGateway(pushgatewayServerUrl);
        return pushGateway;
    }

}
