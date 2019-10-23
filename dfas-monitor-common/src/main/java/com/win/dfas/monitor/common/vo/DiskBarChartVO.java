package com.win.dfas.monitor.common.vo;

import com.win.dfas.monitor.common.vo.cpu.CPUSeriesDataVO;
import com.win.dfas.monitor.common.vo.disk.DiskSeriesDataVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class DiskBarChartVO {

    private List<String> legendData=new ArrayList<>();
    private List<String> xAxisData=new ArrayList<>();
    private List<String> yAxisData=new ArrayList<>();
    private List<String> colorData=new ArrayList<>();
    private List<DiskSeriesDataVO> seriesData=new ArrayList<>();

}
