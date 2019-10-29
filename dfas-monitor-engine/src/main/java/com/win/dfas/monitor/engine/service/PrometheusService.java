package com.win.dfas.monitor.engine.service;

import com.win.dfas.monitor.common.vo.MetricsResultVO;
import com.win.dfas.monitor.common.vo.MicroServiceReqVO;
import com.win.dfas.monitor.common.vo.cpu.CPULineChartMetricsResultVO;
import com.win.dfas.monitor.common.vo.disk.DiskBarChartMetricsResultVO;
import com.win.dfas.monitor.common.vo.jvm.JvmMemoryMetricsResultVO;

import java.util.List;

public interface PrometheusService {

    Double getQps();

    String getQpsOriginData();

    String getQpsChart();

    String getQpsChartOriginData();

    Long getHttpRequestTotal();

    String getHttpRequestTotalOriginData();

    void reload();

    String getJvmMemoryChartOriginData();

    public List<JvmMemoryMetricsResultVO> getJvmMemoryChart(MicroServiceReqVO reqVO);

    public List<CPULineChartMetricsResultVO> getCPULineChart(String ipAddress,String type);

    public List<DiskBarChartMetricsResultVO> getDiskBarChart(String ipAddress, String type);

    public String  getJvmMemory(MicroServiceReqVO reqVO);
}
