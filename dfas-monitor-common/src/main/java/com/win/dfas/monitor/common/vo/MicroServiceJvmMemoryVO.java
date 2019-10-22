package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class MicroServiceJvmMemoryVO {

    private List<String> legendData=new ArrayList<>();
    private List<String> xAxisData=new ArrayList<>();
    private List<String> colorData=new ArrayList<>();
    private List<List<Double>> seriesData=new ArrayList<>();

}
