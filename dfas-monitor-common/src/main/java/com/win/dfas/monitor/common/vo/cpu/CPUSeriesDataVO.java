package com.win.dfas.monitor.common.vo.cpu;

import com.win.dfas.monitor.common.constant.ResultTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CPUSeriesDataVO {
    private String name;
    private String type;
    private String stack;
    private List<Double> data;
}
