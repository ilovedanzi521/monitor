package com.win.dfas.monitor.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.win.dfas.monitor.web.enable.EnableSwaggerConfig;

//@EnableAdminServer
//@EnableDiscoveryClient
@SpringBootApplication
@EnableSwaggerConfig
public class DfasMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfasMonitorApplication.class, args);
    }

}
