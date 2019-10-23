package com.win.dfas.monitor.common.vo.disk;

import com.win.dfas.monitor.common.vo.MetricValueVO;
import com.win.dfas.monitor.common.vo.MetricsResultVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DiskBarChartMetricsResultVO {

    private DiskMetricVO metric ;

    private List<Object> value;

}
