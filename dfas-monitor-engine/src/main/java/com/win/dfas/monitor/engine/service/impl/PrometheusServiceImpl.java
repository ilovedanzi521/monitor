package com.win.dfas.monitor.engine.service.impl;

import com.win.dfas.monitor.common.constant.MonitorConstants;
import com.win.dfas.monitor.common.constant.ResultTypeEnum;
import com.win.dfas.monitor.common.util.DateUtils;
import com.win.dfas.monitor.common.util.JsonUtil;
import com.win.dfas.monitor.common.util.RestfulTools;
import com.win.dfas.monitor.common.vo.*;
import com.win.dfas.monitor.common.vo.cpu.CPULineChartMetricsResultVO;
import com.win.dfas.monitor.common.vo.cpu.CPULineChartMetricsReturnMsgVO;
import com.win.dfas.monitor.common.vo.disk.DiskBarChartMetricsResultVO;
import com.win.dfas.monitor.common.vo.disk.DiskBarChartMetricsReturnMsgVO;
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
    public QpsVO getQpsChart() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("queryParam", "rate(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + "[1h])");
        String url = prometheusServerUrl + "/api/v1/query_range?query={queryParam}&start=" + DateUtils.getStartTime() + "&end=" + DateUtils.getEndTime() + "&step=3600";
        String result = RestfulTools.get(url, String.class, parameters);
        return convertQpsChartMatrixData(result);
    }



    @Override
    public List<MetricsResultVO>  getJvmMemory(MicroServiceReqVO reqVO) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("queryParam", "sum(jvm_memory_used_bytes{application='" + reqVO.getMicroServiceName().toLowerCase() + "',area='heap'}) by (instance)");
        try {
            String url = prometheusServerUrl + "/api/v1/query?query={queryParam}";
            String result = RestfulTools.get(url, String.class, parameters);
            return convertJvmMemoryMomentData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<MetricsResultVO> convertJvmMemoryMomentData(String result) {
       MetricsReturnMsgVO metricsReturnMsgVO = JsonUtil.toObject(result, MetricsReturnMsgVO.class);
        List<MetricsResultVO> metricsResultList = metricsReturnMsgVO.getData().getResult();
        if (metricsResultList != null) {
            for (MetricsResultVO metricsResultVO : metricsResultList) {
                List<Object> valueList = metricsResultVO.getValues();
                if (valueList != null) {
                    for (Object object : valueList) {
                        List values = (ArrayList) object;
                        values.add(DateUtils.doubleToDate((double) values.get(0)));
                    }
                }
            }
        }
        return metricsResultList;
    }

    @Override
    public List<JvmMemoryMetricsResultVO> getJvmMemoryChart(MicroServiceReqVO reqVO) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("queryParam", "sum(jvm_memory_used_bytes{application='" + reqVO.getMicroServiceName().toLowerCase() + "',area='heap'}) by (instance)");
        try {
            String url = prometheusServerUrl + "/api/v1/query_range?query={queryParam}&start=" + DateUtils.getStartTime() + "&end=" + DateUtils.getEndTime() + "&step=345";
            String result = RestfulTools.get(url, String.class, parameters);
            return convertJvmMemoryData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CPULineChartMetricsResultVO> getCPULineChart(String ipAddress, String type) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("queryParam", "avg(irate(node_cpu_seconds_total{instance='expoter_" + ipAddress + "',mode='" + type + "'}[1m])) by (instance)");
        try {
            String  url = prometheusServerUrl + "/api/v1/query_range?query={queryParam}&start=" + DateUtils.getStartTime() + "&end=" + DateUtils.getEndTime() + "&step=345";
            String result = RestfulTools.get(url, String.class, parameters);
            return convertCPULineChartData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DiskBarChartMetricsResultVO> getDiskBarChart(String ipAddress, String type) {
        Map<String, Object> parameters = new HashMap<>();
        if (MonitorConstants.DISK_USED.equals(type)) {
            parameters.put("queryParam", "node_filesystem_size_bytes{instance='expoter_" + ipAddress + "',device!~'rootfs'} - node_filesystem_avail_bytes{instance='expoter_" + ipAddress + "',device!~'rootfs'}");
        } else if (MonitorConstants.DISK_FREE.equals(type)) {
            parameters.put("queryParam", "node_filesystem_avail_bytes{instance='expoter_" + ipAddress + "',device!~'rootfs'}");
        }
        try {
            String  url = prometheusServerUrl + "/api/v1/query?query={queryParam}";
            String result = RestfulTools.get(url, String.class, parameters);
            return convertDiskBarChartData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Double getQps() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("queryParam", "rate(http_requests_total_" + DateUtils.getCurrentDateByStringFormat() + "[1m])");
        String url = prometheusServerUrl + "/api/v1/query?query={queryParam}";
        String result = RestfulTools.get(url, String.class, parameters);
        return Double.valueOf(convertQpsVectorData(result));
    }

    @Override
    public Long getHttpRequestTotal() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("queryParam", "http_requests_total_" + DateUtils.getCurrentDateByStringFormat());
        String url = prometheusServerUrl + "/api/v1/query?query={queryParam}";
        String result = RestfulTools.get(url, String.class, parameters);
        return Long.parseLong(convertQpsVectorData(result));
    }


    @Override
    public void reload() {
        String url = prometheusServerUrl + "/-/reload";
        RestfulTools.post(url, String.class);
    }

    private List<DiskBarChartMetricsResultVO> convertDiskBarChartData(String result) {
        DiskBarChartMetricsReturnMsgVO metricsReturnMsgVO = JsonUtil.toObject(result, DiskBarChartMetricsReturnMsgVO.class);
        List<DiskBarChartMetricsResultVO> metricsResultList = metricsReturnMsgVO.getData().getResult();
        return metricsResultList;
    }

    private List<CPULineChartMetricsResultVO> convertCPULineChartData(String result) {
        CPULineChartMetricsReturnMsgVO metricsReturnMsgVO = JsonUtil.toObject(result, CPULineChartMetricsReturnMsgVO.class);
        List<CPULineChartMetricsResultVO> metricsResultList = metricsReturnMsgVO.getData().getResult();
        if (metricsResultList != null) {
            List<String> allTimeList = new ArrayList<>();
            for (CPULineChartMetricsResultVO metricsResultVO : metricsResultList) {
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
            for (CPULineChartMetricsResultVO metricsResultVO : metricsResultList) {
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
                    if (!metricsResultVO.getExistTimesList().contains(time)) {
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

    private String convertQpsVectorData(String result) {
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

    private QpsVO convertQpsChartMatrixData(String result) {
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
        return qps;
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
                    if (!metricsResultVO.getExistTimesList().contains(time)) {
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
