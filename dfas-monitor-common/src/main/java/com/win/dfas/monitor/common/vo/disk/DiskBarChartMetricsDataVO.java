package com.win.dfas.monitor.common.vo.disk;

import com.win.dfas.monitor.common.constant.ResultTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DiskBarChartMetricsDataVO {

    private ResultTypeEnum resultType;

    private List<DiskBarChartMetricsResultVO> result;

}
