package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class QpsVO {

    private List<String> xAxisData = new ArrayList<>();

    private List<Double> yAxisData = new ArrayList<>();

}
