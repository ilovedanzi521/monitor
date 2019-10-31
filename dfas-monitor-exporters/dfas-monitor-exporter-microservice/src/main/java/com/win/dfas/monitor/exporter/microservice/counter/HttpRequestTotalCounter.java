package com.win.dfas.monitor.exporter.microservice.counter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.vo.MetricsResultVO;
import com.win.dfas.monitor.common.vo.MetricsReturnMsgVO;
import com.win.dfas.monitor.exporter.microservice.pushgateway.MonitorPushGateway;

import io.prometheus.client.Counter;
import io.prometheus.client.exporter.PushGateway;

@Component
@Import({ MonitorPushGateway.class })
public class HttpRequestTotalCounter {
	
	@Value("${prometheus.server.url}")
	private String prometheusServerUrl;

	@Autowired
	private PushGateway pushGateway;

	private static Counter requests = Counter.build().name("http_requests_total_"+DateUtils.getCurrentDateByStringFormat()).help("请求数").register();
	
	private volatile boolean hasInit = false;

	private synchronized void init() {
		if (!hasInit) {
			try {
				long number = getHttpRequestTotal()+1;
				requests.inc(number);
				pushGateway.push(requests, "pushgateway-microservice");
				hasInit = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			execute();
		}
	}

	private void execute() {
		try {
			requests.inc();
			pushGateway.push(requests, "pushgateway-microservice");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void inc() {
		if (!hasInit) {
			init();
		} else {
			execute();
		}
	}

	public void inc(String requestUrl) {
		if(requestUrl != null) {
			if(requestUrl.contains("dfas-")|| requestUrl.contains("dfbp-")) {
				if (!hasInit) {
					init();
				} else {
					execute();
				}
			}
		}	
	}
	
    public Long getHttpRequestTotal() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("queryParam", "http_requests_total_" + DateUtils.getCurrentDateByStringFormat());
        String url = prometheusServerUrl + "/api/v1/query?query={queryParam}";
        String result = RestfulTools.get(url, String.class, parameters);
        return Long.parseLong(convertVectorData(result));
    }


    private String convertVectorData(String result) {
        MetricsReturnMsgVO metricsReturnMsgVO = JsonUtil.toObject(result, MetricsReturnMsgVO.class);
        List<MetricsResultVO> metricsResultList = metricsReturnMsgVO.getData().getResult();
        if (metricsResultList != null) {
            for (MetricsResultVO metricsResultVO : metricsResultList) {
                List<Object> valueList = metricsResultVO.getValue();
                return String.valueOf(valueList.get(1));
            }
        }
        return "0";
    }
    
}
