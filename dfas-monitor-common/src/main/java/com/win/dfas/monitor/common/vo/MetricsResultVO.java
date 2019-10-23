package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class MetricsResultVO {

    protected Map<String, String> metric;

    protected List<Object> values;

    protected List<Object> value;
}
