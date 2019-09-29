package com.win.dfas.monitor.web;

import com.win.dfas.monitor.config.EnableMonitorWebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.win.dfas.monitor.config.EnableSwaggerConfig;
import com.win.dfas.monitor.exporter.microservice.EnableMonitorConfig;

/*@EnableAdminServer*/
//@EnableDiscoveryClient
@EnableMonitorConfig
@SpringBootApplication(scanBasePackages={"com.win.dfas.monitor.engine", "com.win.dfas.monitor.web"})
@EnableSwaggerConfig
@EnableMonitorWebSocket
public class DfasMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfasMonitorApplication.class, args);
    }

}
