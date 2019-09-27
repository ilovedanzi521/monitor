package com.win.dfas.monitor.engine.service.impl;

import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.engine.service.QpsService;
import org.springframework.stereotype.Service;

@Service
public class QpsServiceImpl implements QpsService {

    @Override
    public String getQps() {
    	
    	String url = "http://192.168.0.55:9090/api/v1/query_range?query=increase(http_requests_total_" + DateUtils.getCurrentDateByStringFormat()+"[1m])&start="+DateUtils.getStartTime()+"&end="+DateUtils.getEndTime()+"&step=1";
		//url="http://192.168.0.55:9090/api/v1/query_range?query=increase(http_requests_total_20190926[1m])&start=1569502475.670&end=1569502775.670&step=1";
		System.out.println(url);
		String result = RestfulTools.get(url, String.class);
    	
        return result;
    }
}
