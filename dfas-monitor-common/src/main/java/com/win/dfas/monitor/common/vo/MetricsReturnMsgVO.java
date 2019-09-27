package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MetricsReturnMsgVO {

	private String status;
	
	private MetricsDataVO data;
}
