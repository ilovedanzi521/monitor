package com.win.dfas.monitor.exporter.microservice.controller;

import io.prometheus.client.Gauge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GaugeDemo {

    /**
     * 指标注册
     * name设置指标名
     * labelNames设置各项指标名称
     * help设置指标描述
     */
    static final Gauge gaugeDemo = Gauge.build()
            .name("gaugeDemo")
            .labelNames("label1", "label2", "label3", "label4", "label5")
            .help("gauge 实例").register();

    /**
     * 指标埋点
     */
    @Scheduled(cron = "0/5 * * * * ?")
    @RequestMapping("/changeGauge")
    public void changeGauge() {
        gaugeDemo.labels("1", "2", "3", "4", "5").inc(); //指标值加1
        gaugeDemo.labels("1", "2", "3", "4", "5").dec(); //指标值减一
        gaugeDemo.labels("1", "2", "3", "4", "5").set(19.00); //指标值直接赋值
    }
}
