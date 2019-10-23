package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class MachineCPUUsageVO {
    private List<String> legendData=new ArrayList<>();
    private List<String> xAxisData=new ArrayList<>();
    private List<String> colorData=new ArrayList<>();
    private List<Map<String,Object>> seriesData=new ArrayList<>();
}
