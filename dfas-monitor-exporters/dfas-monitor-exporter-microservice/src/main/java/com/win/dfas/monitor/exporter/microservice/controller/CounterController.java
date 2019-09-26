package com.win.dfas.monitor.exporter.microservice.controller;

import io.prometheus.client.Counter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {

    /**
     *  使用Counter.build()创建Counter类型的监控指标，并且通过name()方法定义监控指标的名称network_traffic_input
     * ，通过labelNames()定义该指标包含的标签。最后通过register()将该指标注册到Collector的defaultRegistry中
     */
    static final Counter counterDemo = Counter.build()
            .name("counterChanger2").labelNames("wy", "zxjr", "ocs", "xxjf", "unit")
            .help("Counter 实例").register();

    /**
     * 指标埋点，定时器会造成普罗米修斯与本地的数据时间戳不同步，尽量不要使用这种方式，实例中的定时器是为了数据演示
     */
    @Scheduled(cron = "0/5 * * * * ?")
    @RequestMapping("/changeCounter")
    public void changeCounter() {
        counterDemo.labels("网元", "在线接入", "OCS", "消息计费", "seconds").inc();//指标值增加
    }

}
