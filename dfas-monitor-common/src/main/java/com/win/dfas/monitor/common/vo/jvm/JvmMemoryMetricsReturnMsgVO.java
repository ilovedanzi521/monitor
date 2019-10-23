package com.win.dfas.monitor.common.vo.jvm;

import com.win.dfas.monitor.common.vo.MetricsDataVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JvmMemoryMetricsReturnMsgVO {

	private String status;
	
	private JvmMemoryMetricsDataVO data;
}
