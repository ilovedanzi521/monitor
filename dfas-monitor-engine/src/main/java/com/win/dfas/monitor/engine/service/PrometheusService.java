package com.win.dfas.monitor.engine.service;

public interface PrometheusService {

    Double getQps();

    String getQpsOriginData();

    String getQpsChart();

    String getQpsChartOriginData();

    Long getHttpRequestTotal();

    String getHttpRequestTotalOriginData();
}
