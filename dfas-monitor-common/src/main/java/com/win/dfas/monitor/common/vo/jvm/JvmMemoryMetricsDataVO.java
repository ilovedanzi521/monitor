package com.win.dfas.monitor.common.vo.jvm;

import com.win.dfas.monitor.common.constant.ResultTypeEnum;
import com.win.dfas.monitor.common.vo.MetricsResultVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class JvmMemoryMetricsDataVO {

    private ResultTypeEnum resultType;

    private List<JvmMemoryMetricsResultVO> result;

}
