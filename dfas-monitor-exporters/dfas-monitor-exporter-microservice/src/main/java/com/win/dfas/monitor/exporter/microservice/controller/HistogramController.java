package com.win.dfas.monitor.exporter.microservice.controller;

import io.prometheus.client.Histogram;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistogramController {
    
    /**
     * 注册
     * 注册时buckets()设置区间值，如下设置了100、200、300三个区间值
     */
    static final Histogram histogramDemo = Histogram.build()
            .labelNames("label1", "label2", "label3", "label4", "label5")
            .name("histogramDemo")
            .buckets(100, 200, 300)
            .help("Histogram 实例")
            .register();

    /**
     * 指标埋点
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void changeHistogram() {
        /**
         * 本次执行的指标值
         * 如下设置为150，则每次执行，小于200区间以及小于300区间加1，小于100区间不变
         */
        histogramDemo.labels("1", "2", "3", "4", "5").observe(150);
    }
}
