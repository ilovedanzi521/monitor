package com.win.dfas.monitor.exporter.microservice.log;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.MDC;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.NetUtils;

import ch.qos.logback.core.PropertyDefinerBase;

@Component
public class LogCustomField  extends PropertyDefinerBase implements ApplicationListener<WebServerInitializedEvent> {

	
	private int serverPort;
	
	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		serverPort = event.getWebServer().getPort();
	}
	
    @Override
    public String getPropertyValue() {
    	Map<String,String> customFieldMap=new HashMap<>();
    	try {
			customFieldMap.put("ip",NetUtils.getLocalIpAddress());
		} catch (SocketException e) {
			e.printStackTrace();
		}
    	customFieldMap.put("port",String.valueOf(serverPort));
        return JsonUtil.toJson(customFieldMap);
    }

	

}
