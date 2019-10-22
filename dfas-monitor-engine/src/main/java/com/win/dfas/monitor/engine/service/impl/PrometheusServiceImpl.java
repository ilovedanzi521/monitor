package com.win.dfas.monitor.engine.service.impl;

import com.win.dfas.monitor.common.constant.ResultTypeEnum;
import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.vo.*;
import com.win.dfas.monitor.common.vo.jvm.JvmMemoryMetricsResultVO;
import com.win.dfas.monitor.common.vo.jvm.JvmMemoryMetricsReturnMsgVO;
import com.win.dfas.monitor.engine.service.PrometheusService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.*;

@Service
public class PrometheusServiceImpl implements PrometheusService {

    @Value("${prometheus.server.url}")
    private String prometheusServerUrl;

    @Value("${registration.center.url}")
    private String registrationCenterUrl;

    /** 非千分位格式化 */
    protected NumberFormat noneThousandBitNumberFormat = NumberFormat.getNumberInstance();

    @Override
    public String getQpsChart() {
        // String url = prometheusServerUrl + "/api/v1/query_range?query=increase(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + "[1m])&start=" + DateUtils.getStartTime() + "&end=" + DateUtils.getEndTime() + "&step=60";
        String url = prometheusServerUrl + "/api/v1/query_range?query=rate(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + "[1h])&start=" + DateUtils.getStartTime() + "&end=" + DateUtils.getEndTime() + "&step=3600";
        String result = RestfulTools.get(url, String.class);
        return convertMatrixData(result);
    }

    @Override
    public String getQpsChartOriginData() {
        //String url = prometheusServerUrl + "/api/v1/query_range?query=increase(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + "[1m])&start=" + DateUtils.getStartTime() + "&end=" + DateUtils.getEndTime() + "&step=60";
        String url = prometheusServerUrl + "/api/v1/query_range?query=rate(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + "[1h])&start=" + DateUtils.getStartTime() + "&end=" + DateUtils.getEndTime() + "&step=3600";
        String result = RestfulTools.get(url, String.class);
        return convert(result);
    }

    @Override
    public List<JvmMemoryMetricsResultVO> getJvmMemoryChart(MicroServiceReqVO reqVO) {
        //String url = prometheusServerUrl + "/api/v1/query_range?query=jvm_memory_used_bytes&start=1571612606.68&end=1571655806.68&step=172";
        //  String queryParam = "sum(jvm_memory_used_bytes{area='heap'})";
        Map<String, Object> parameters = new HashMap<>();
        //parameters.put("queryParam", "sum(jvm_memory_used_bytes{area='heap'})");
        parameters.put("queryParam", "sum(jvm_memory_used_bytes{application='"+reqVO.getMicroServiceName().toLowerCase()+"',area='heap'}) by (instance)");
        String url = null;
        try {
            //url = prometheusServerUrl + "/api/v1/query_range?query={queryParam}&start=1571612606.68&end=1571655806.68&step=172";
            url = prometheusServerUrl + "/api/v1/query_range?query={queryParam}&start=" + DateUtils.getStartTime() + "&end=" + DateUtils.getEndTime() + "&step=345";
            String result = RestfulTools.get(url, String.class, parameters);
            return convertJvmMemoryData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getJvmMemoryChartOriginData() {
        //String url = prometheusServerUrl + "/api/v1/query_range?query=jvm_memory_used_bytes&start=1571612606.68&end=1571655806.68&step=172";
        //  String queryParam = "sum(jvm_memory_used_bytes{area='heap'})";
        Map<String, Object> parameters = new HashMap<>();
        //parameters.put("queryParam", "sum(jvm_memory_used_bytes{area='heap'})");
        parameters.put("queryParam", "sum(jvm_memory_used_bytes{area='heap'}) by (instance)");
        String url = null;
        try {
            //url = prometheusServerUrl + "/api/v1/query_range?query={queryParam}&start=1571612606.68&end=1571655806.68&step=172";
            url = prometheusServerUrl + "/api/v1/query_range?query={queryParam}&start=" + DateUtils.getStartTime() + "&end=" + DateUtils.getEndTime() + "&step=345";
            String result = RestfulTools.get(url, String.class, parameters);
            return convert(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Double getQps() {
        //String url = prometheusServerUrl + "/api/v1/query?query=increase(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + "[1m])";
        String url = prometheusServerUrl + "/api/v1/query?query=rate(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + "[1m])";
        String result = RestfulTools.get(url, String.class);
        return Double.valueOf(convertVectorData(result));
    }

    @Override
    public String getQpsOriginData() {
        String url = prometheusServerUrl + "/api/v1/query?query=increase(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + "[1m])";
        String result = RestfulTools.get(url, String.class);
        return convert(result);
    }

    @Override
    public Long getHttpRequestTotal() {
        String url = prometheusServerUrl + "/api/v1/query?query=sum(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + ")";
        String result = RestfulTools.get(url, String.class);
        return Long.parseLong(convertVectorData(result));
    }

    @Override
    public String getHttpRequestTotalOriginData() {
        String url = prometheusServerUrl + "/api/v1/query?query=sum(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + ")";
        String result = RestfulTools.get(url, String.class);
        return convert(result);
    }

    @Override
    public void reload() {
        String url = prometheusServerUrl + "/-/reload";
        RestfulTools.post(url, String.class);
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

    private String convertMatrixData(String result) {
        MetricsReturnMsgVO metricsReturnMsgVO = JsonUtil.toObject(result, MetricsReturnMsgVO.class);
        List<MetricsResultVO> metricsResultList = metricsReturnMsgVO.getData().getResult();
        QpsVO qps = new QpsVO();

        List<String> xAxisData = new ArrayList<>();
        List<Double> yAxisData = new ArrayList<>();

        noneThousandBitNumberFormat.setGroupingUsed(false);

        qps.setXAxisData(xAxisData);
        qps.setYAxisData(yAxisData);
        if (metricsResultList != null) {
            for (MetricsResultVO metricsResultVO : metricsResultList) {
                List<Object> valueList = metricsResultVO.getValues();
                if (valueList != null) {
                    for (Object object : valueList) {
                        List values = (ArrayList) object;
                        xAxisData.add(DateUtils.doubleToDateOnlyHourMinute((double) values.get(0)));
                        yAxisData.add(Double.parseDouble(noneThousandBitNumberFormat.format(Double.parseDouble(String.valueOf(values.get(1))))));
                    }
                }
            }
        }
        return JsonUtil.toJson(qps);
    }

    private List<JvmMemoryMetricsResultVO> convertJvmMemoryData(String result) {
        JvmMemoryMetricsReturnMsgVO metricsReturnMsgVO = JsonUtil.toObject(result, JvmMemoryMetricsReturnMsgVO.class);
        List<JvmMemoryMetricsResultVO> metricsResultList = metricsReturnMsgVO.getData().getResult();
        if (metricsResultList != null) {
            List<String> allTimeList = new ArrayList<>();
            for (JvmMemoryMetricsResultVO metricsResultVO : metricsResultList) {
                List<Object> valueList = metricsResultVO.getValues();
                List<String> strTimeList = new ArrayList<>();
                if (valueList != null) {
                    for (Object object : valueList) {
                        List values = (ArrayList) object;
                        values.add(DateUtils.doubleToDate((double) values.get(0)));
                        if (!strTimeList.contains(values.get(2))) {
                            strTimeList.add(String.valueOf(values.get(2)));
                        }
                        if (!allTimeList.contains(values.get(2))) {
                            allTimeList.add(String.valueOf(values.get(2)));
                        }
                    }
                }
                metricsResultVO.setExistTimesList(strTimeList);
            }
            Collections.sort(allTimeList);
            for (JvmMemoryMetricsResultVO metricsResultVO : metricsResultList) {
                List<Object> valueList = metricsResultVO.getValues();
                metricsResultVO.setAllTimesList(allTimeList);
                List<MetricValueVO> metricValueList = new ArrayList<>();
                for (Object object : valueList) {
                    MetricValueVO metricValueVO = new MetricValueVO();
                    List values = (ArrayList) object;
                    values.add(DateUtils.doubleToDate((double) values.get(0)));
                    metricValueVO.setValue((String) values.get(1));
                    metricValueVO.setStrTime(DateUtils.doubleToDate((double) values.get(0)));
                    metricValueList.add(metricValueVO);
                }
                for (String time : allTimeList) {
                    if(!metricsResultVO.getExistTimesList().contains(time)){
                        MetricValueVO metricValueVO = new MetricValueVO();
                        metricValueVO.setValue("0");
                        metricValueVO.setStrTime(time);
                        metricValueList.add(metricValueVO);
                    }
                }
                Collections.sort(metricValueList);
                metricsResultVO.setMetricValueList(metricValueList);
            }
        }
        return metricsResultList;
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
