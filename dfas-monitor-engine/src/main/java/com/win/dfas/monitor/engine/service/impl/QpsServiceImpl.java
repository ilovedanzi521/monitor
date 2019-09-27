package com.win.dfas.monitor.engine.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.vo.MetricsResultVO;
import com.win.dfas.monitor.common.vo.MetricsReturnMsgVO;
import com.win.dfas.monitor.engine.service.QpsService;

@Service
public class QpsServiceImpl implements QpsService {

    @Override
    public String getQps() {
    	
    	String url = "http://192.168.0.55:9090/api/v1/query_range?query=increase(http_requests_total_" + DateUtils.getCurrentDateByStringFormat()+"[1m])&start="+DateUtils.getStartTime()+"&end="+DateUtils.getEndTime()+"&step=60";
		//url="http://192.168.0.55:9090/api/v1/query_range?query=increase(http_requests_total_20190926[1m])&start=1569502475.670&end=1569502775.670&step=1";
		System.out.println(url);
		String result = RestfulTools.get(url, String.class);
        MetricsReturnMsgVO metricsReturnMsgVO=JsonUtil.toObject(result,MetricsReturnMsgVO.class);
        List<MetricsResultVO> metricsResultList=metricsReturnMsgVO.getData().getResult();
        if(metricsResultList != null) {
        	for(MetricsResultVO metricsResultVO:metricsResultList) {
        		List<Object> valueList=metricsResultVO.getValues();
        		if(valueList != null) {
        			for(Object object:valueList) {
        				if(object instanceof ArrayList) {
        					List values=(ArrayList)object;
							double dateTime=(double)values.get(0);
							String strDate=DateUtils.doubleToDate(dateTime);
							System.out.println(strDate);
							values.add(strDate);
        				}
        			}
        		}
        	}
        }
        return JsonUtil.toJson(metricsReturnMsgVO);
    }
}
