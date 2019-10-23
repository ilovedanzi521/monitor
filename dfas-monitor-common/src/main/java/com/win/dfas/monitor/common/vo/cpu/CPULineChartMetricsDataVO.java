package com.win.dfas.monitor.common.vo.cpu;

import com.win.dfas.monitor.common.constant.ResultTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CPULineChartMetricsDataVO {

    private ResultTypeEnum resultType;

    private List<CPULineChartMetricsResultVO> result;

}
