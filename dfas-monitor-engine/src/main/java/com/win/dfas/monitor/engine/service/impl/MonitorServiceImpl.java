package com.win.dfas.monitor.engine.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.win.dfas.common.util.ObjectUtils;
import com.win.dfas.monitor.common.constant.ResultTypeEnum;
import com.win.dfas.monitor.common.entity.MicroServiceEntity;
import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.vo.*;
import com.win.dfas.monitor.config.mapper.MicroServiceMapper;
import com.win.dfas.monitor.engine.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private MicroServiceMapper microServiceMapper;

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

    @Override
    public PageInfo<MicroServiceRepVO> getMicroServiceList(MicroServiceReqVO reqVO) {
        PageHelper.startPage(reqVO.getReqPageNum(),reqVO.getReqPageSize());
        List<MicroServiceEntity> list = microServiceMapper.selectMicroServiceList(reqVO);
        PageInfo<MicroServiceEntity> info = new PageInfo<MicroServiceEntity>(list);
        return ObjectUtils.copyPageInfo(info, MicroServiceRepVO.class);
    }


    public Map<String, Object> getMicroServiceList3() {
        Random random = new Random(System.currentTimeMillis());
        List<MicroServiceStatusVO> microServiceStatusList = new ArrayList<>();
        int count = 70 + random.nextInt(10);
        for (int i = 0; i < count; i++) {
            MicroServiceStatusVO microServiceStatus = new MicroServiceStatusVO();
            microServiceStatus.setId(String.valueOf(i));
            microServiceStatus.setName("订单服务" + i);
            microServiceStatus.setWarn(random.nextInt(500));
            microServiceStatus.setError(random.nextInt(5000));
            microServiceStatus.setState(String.valueOf(random.nextInt(3) + 1));
            microServiceStatusList.add(microServiceStatus);
        }
        Map<String, Object> retList = new HashMap<>();
        retList.put("list", microServiceStatusList);
        return retList;
    }


    public String getMicroServiceList2() {
        String url = registrationCenterUrl + "/eureka/apps";
        String result = RestfulTools.get(url, String.class);
        return result;
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
