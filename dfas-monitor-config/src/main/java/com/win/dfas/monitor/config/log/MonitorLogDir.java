package com.win.dfas.monitor.config.log;

import ch.qos.logback.core.PropertyDefinerBase;
import org.springframework.stereotype.Component;

@Component
public class MonitorLogDir extends PropertyDefinerBase{

    @Override
    public String getPropertyValue() {
        return System.getProperty("user.dir")+"/logs";
    }
}
