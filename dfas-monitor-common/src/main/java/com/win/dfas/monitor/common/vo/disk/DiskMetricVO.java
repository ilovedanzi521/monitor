package com.win.dfas.monitor.common.vo.disk;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DiskMetricVO {
    private String device;
    private String fstype;
    private String instance;
    private String job;
    private String mountpoint;
}
