package com.win.dfas.monitor.common.vo.cpu;

		import lombok.Getter;
		import lombok.Setter;
		import lombok.ToString;

@Getter
@Setter
@ToString
public class CPULineChartMetricsReturnMsgVO {

	private String status;

	private CPULineChartMetricsDataVO data;
}
