package com.win.dfas.monitor.engine.pool;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ThreadpoolInitializerConfiguration {

    @PostConstruct
    public void init() {
        ScheduledThreadPool.init();
    }

}
