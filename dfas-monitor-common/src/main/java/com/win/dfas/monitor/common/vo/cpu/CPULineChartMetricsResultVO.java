package com.win.dfas.monitor.common.vo.cpu;

import com.win.dfas.monitor.common.vo.MetricValueVO;
import com.win.dfas.monitor.common.vo.MetricsResultVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CPULineChartMetricsResultVO extends MetricsResultVO {

    private List<MetricValueVO> metricValueList;

    private  List<String> existTimesList;

    private  List<String> allTimesList;
}
