package com.win.dfas.monitor.exporter.microservice.metrics;

import com.win.dfas.monitor.common.util.DateUtils;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.PushGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class JvmMemoryMetrics {

    @Value("${prometheus.server.url}")
    private String prometheusServerUrl;

    @Scheduled(cron = "0/5 * * * * ?")
    public void pushExceptionData() {
        counter();
    }

    private void counter() {
        Counter counter = getCounter();
        counter.labels("服务名要定义", "ip无法获取").inc();
        try {
            pushGateway.push(counter, "pushgateway-microservice");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public PushGateway buildPushGateway() {
        pushGateway = new PushGateway(prometheusServerUrl);
        return pushGateway;
    }

    private PushGateway pushGateway;

    private final Map<String, Counter> countMap = new ConcurrentHashMap<>();


    private Counter getCounter() {
        String currDate = DateUtils.getCurrentDateByStringFormat();
        Counter counter = countMap.get(currDate);
        if (counter == null) {
            counter = Counter.build()
                    .name("jvm_memory_" + currDate)
                    //标签名
                    .labelNames("service", "ip")
                    .help(currDate + "jvm内存")
                    .register();
            countMap.put(currDate, counter);
        }
        return counter;
    }

}
