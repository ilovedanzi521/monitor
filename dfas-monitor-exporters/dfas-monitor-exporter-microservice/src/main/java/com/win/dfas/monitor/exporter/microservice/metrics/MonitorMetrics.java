package com.win.dfas.monitor.exporter.microservice.metrics;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MonitorMetrics {

    /**
     *  默认为空,程序使用method signature作为Metric name
     *  如果name有设置值,使用name作为Metric name
     * @return
     */
    String name() default "";

}