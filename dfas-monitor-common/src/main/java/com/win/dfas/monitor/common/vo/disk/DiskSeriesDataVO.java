package com.win.dfas.monitor.common.vo.disk;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DiskSeriesDataVO {
    private String name;
    private String type;
    private List<Double> data;
}
