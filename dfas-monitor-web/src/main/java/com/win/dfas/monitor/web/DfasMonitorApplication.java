package com.win.dfas.monitor.web;

import com.win.dfas.monitor.engine.MonitorEngineInitializer;
import com.win.dfas.monitor.exporter.microservice.EnableMonitorConfig;
import io.micrometer.core.instrument.MeterRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/*@EnableAdminServer*/
//@EnableDiscoveryClient
@EnableEurekaClient
@EnableMonitorConfig
@EnableScheduling
@MapperScan("com.win.dfas.monitor.config.mapper")
@SpringBootApplication(scanBasePackages = {"com.win.dfas.monitor.engine", "com.win.dfas.monitor.web", "com.win.dfas.monitor.config", "com.win.dfas.monitor.common.util"})
@Import(MonitorEngineInitializer.class)
public class DfasMonitorApplication {

    public static void main(String[] args) {
        //System.setProperty("h2.baseDir",Thread.currentThread().getContextClassLoader().getResource("").getPath());
    	
    	System.setProperty("h2.baseDir", System.getProperty("user.dir"));
        SpringApplication.run(DfasMonitorApplication.class, args);
        //DefaultExports.initialize();
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }

}
