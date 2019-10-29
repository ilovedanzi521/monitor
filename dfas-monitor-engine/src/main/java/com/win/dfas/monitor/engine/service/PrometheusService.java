package com.win.dfas.monitor.engine.service;

import com.win.dfas.monitor.common.vo.MetricsResultVO;
import com.win.dfas.monitor.common.vo.MicroServiceReqVO;
import com.win.dfas.monitor.common.vo.QpsVO;
import com.win.dfas.monitor.common.vo.cpu.CPULineChartMetricsResultVO;
import com.win.dfas.monitor.common.vo.disk.DiskBarChartMetricsResultVO;
import com.win.dfas.monitor.common.vo.jvm.JvmMemoryMetricsResultVO;

import java.util.List;

public interface PrometheusService {

    Double getQps();

    QpsVO getQpsChart();

    Long getHttpRequestTotal();

    void reload();

    List<JvmMemoryMetricsResultVO> getJvmMemoryChart(MicroServiceReqVO reqVO);

    List<CPULineChartMetricsResultVO> getCPULineChart(String ipAddress, String type);

    List<DiskBarChartMetricsResultVO> getDiskBarChart(String ipAddress, String type);

    List<MetricsResultVO> getJvmMemory(MicroServiceReqVO reqVO);
}
