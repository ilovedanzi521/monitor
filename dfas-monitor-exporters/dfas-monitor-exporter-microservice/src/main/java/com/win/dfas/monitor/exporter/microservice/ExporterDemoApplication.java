package com.win.dfas.monitor.exporter.microservice;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class ExporterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExporterDemoApplication.class, args);
    }
}