package com.win.dfas.monitor.exporter.microservice;

import com.win.dfas.monitor.exporter.microservice.center.AbstractMonitorCenter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class RequestCountTest {

    @Test
    public void test() {
        for (int i = 0; i < 50; i++) {
            AbstractMonitorCenter.instance().open("test");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
