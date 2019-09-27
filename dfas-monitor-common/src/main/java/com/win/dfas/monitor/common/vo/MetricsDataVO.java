package com.win.dfas.monitor.common.vo;

import com.win.dfas.monitor.common.ResultTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MetricsDataVO {

    private ResultTypeEnum resultType;

    private List<MetricsResultVO> result;

}
