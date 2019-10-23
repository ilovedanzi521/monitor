package com.win.dfas.monitor.common.vo.disk;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DiskBarChartMetricsReturnMsgVO {

	private String status;

	private DiskBarChartMetricsDataVO data;
}
