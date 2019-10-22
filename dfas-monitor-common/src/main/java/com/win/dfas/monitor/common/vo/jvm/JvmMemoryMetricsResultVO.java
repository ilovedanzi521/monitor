package com.win.dfas.monitor.common.vo.jvm;

import com.win.dfas.monitor.common.vo.MetricValueVO;
import com.win.dfas.monitor.common.vo.MetricsResultVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class JvmMemoryMetricsResultVO extends MetricsResultVO {

    private List<MetricValueVO> metricValueList;

    private  List<String> existTimesList;

    private  List<String> allTimesList;
}
