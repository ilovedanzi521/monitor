package com.win.dfas.monitor.engine.service.impl;

import com.win.dfas.monitor.common.constant.ResultTypeEnum;
import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.vo.MetricsResultVO;
import com.win.dfas.monitor.common.vo.MetricsReturnMsgVO;
import com.win.dfas.monitor.engine.service.PrometheusService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrometheusServiceImpl implements PrometheusService {

    @Value("${prometheus.server.url}")
    private String prometheusServerUrl;

    @Value("${registration.center.url}")
    private String registrationCenterUrl;

    @Override
    public String getQps() {
        String url = prometheusServerUrl + "/api/v1/query_range?query=increase(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + "[1m])&start=" + DateUtils.getStartTime() + "&end=" + DateUtils.getEndTime() + "&step=60";
        String result = RestfulTools.get(url, String.class);
        return convert(result);
    }

    @Override
    public String getHttpRequestTotal() {
        String url = prometheusServerUrl + "/api/v1/query?query=sum(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + ")";
        String result = RestfulTools.get(url, String.class);
        return convert(result);
    }


    private String convert(String result) {
        MetricsReturnMsgVO metricsReturnMsgVO = JsonUtil.toObject(result, MetricsReturnMsgVO.class);
        List<MetricsResultVO> metricsResultList = metricsReturnMsgVO.getData().getResult();
        ResultTypeEnum resultType = metricsReturnMsgVO.getData().getResultType();
        if (metricsResultList != null) {
            for (MetricsResultVO metricsResultVO : metricsResultList) {
                if (resultType == ResultTypeEnum.matrix) {
                    List<Object> valueList = metricsResultVO.getValues();
                    if (valueList != null) {
                        for (Object object : valueList) {
                            List values = (ArrayList) object;
                            values.add(DateUtils.doubleToDate((double) values.get(0)));
                        }
                    }
                } else {
                    List<Object> valueList = metricsResultVO.getValue();
                    valueList.add(DateUtils.doubleToDate((double) valueList.get(0)));
                }
            }
        }
        return JsonUtil.toJson(metricsReturnMsgVO);
    }

}