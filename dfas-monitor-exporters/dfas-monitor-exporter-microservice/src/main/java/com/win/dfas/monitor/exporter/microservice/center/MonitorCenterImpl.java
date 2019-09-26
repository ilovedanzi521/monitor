package com.win.dfas.monitor.exporter.microservice.center;


import com.win.dfas.monitor.common.util.DateUtils;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.PushGateway;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MonitorCenterImpl extends AbstractMonitorCenter {

    private static PushGateway pushGateway = new PushGateway("192.168.0.56:9091");

    private static final Map<String, Counter> countMap = new ConcurrentHashMap<>();
    
    private static final Map<String, Counter> urlCountMap = new ConcurrentHashMap<>();

    private Counter getCounter() {
        String currDate = DateUtils.getCurrentDateByStringFormat();
        Counter counter = countMap.get(currDate);
        if (counter == null) {
            counter = Counter.build()
                    .name("http_requests_total_" + currDate)
                    //标签名
                    .labelNames("scope","instance")
                    .help(currDate+"请求数")
                    .register();
            countMap.put(currDate, counter);
        }
        return counter;
    }
    
    private Counter getUrlCounter() {
        String currDate = DateUtils.getCurrentDateByStringFormat();
        Counter counter = urlCountMap.get(currDate);
        if (counter == null) {
            counter = Counter.build()
                    .name("http_requests_url_total_" + currDate)
                    //标签名
                    .labelNames("request_url", "instance")
                    .help(currDate+"请求数")
                    .register();
            urlCountMap.put(currDate, counter);
        }
        return counter;
    }

    @Override
    public void open() {
        try {
            Counter counter = getCounter();
            counter.labels("all","pushgateway-microservice").inc();
            pushGateway.push(counter, "pushgateway-microservice");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void open(String name) {
        try {
            Counter counter = getUrlCounter();
            counter.labels(name, "pushgateway-microservice").inc();
            pushGateway.push(counter, "pushgateway-microservice");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例
     * @author wangyh
     */
    private static class SingletonHolder {
        public final static MonitorCenterImpl instance = new MonitorCenterImpl();
    }

    /**
     * 获取单例
     * @return
     */
    public static AbstractMonitorCenter getInstance() {
        return SingletonHolder.instance;
    }

}
