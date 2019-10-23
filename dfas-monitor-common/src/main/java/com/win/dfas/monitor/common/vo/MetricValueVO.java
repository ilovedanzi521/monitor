package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MetricValueVO implements Comparable<MetricValueVO> {

    private String value;
    private String strTime;


    @Override
    public int compareTo(MetricValueVO metricValueVO) {
        if (metricValueVO.getStrTime().compareTo(strTime) > 0) {
            return -1;
        } else if (metricValueVO.getStrTime().compareTo(strTime) < 0) {
            return 1;
        }
        return 0;
    }
}
