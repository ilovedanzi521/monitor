package com.win.dfas.monitor.web;

import com.win.dfas.monitor.engine.MonitorEngineInitializer;
import com.win.dfas.monitor.exporter.microservice.EnableMonitorConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.net.URL;

/*@EnableAdminServer*/
//@EnableDiscoveryClient
@EnableMonitorConfig
@MapperScan("com.win.dfas.monitor.config.mapper")
@SpringBootApplication(scanBasePackages = {"com.win.dfas.monitor.engine", "com.win.dfas.monitor.web", "com.win.dfas.monitor.config"})
@Import(MonitorEngineInitializer.class)
public class DfasMonitorApplication {

    public static void main(String[] args) {
       //System.setProperty("h2.baseDir",Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.setProperty("h2.baseDir",System.getProperty("user.dir"));
        SpringApplication.run(DfasMonitorApplication.class, args);
    }

}
