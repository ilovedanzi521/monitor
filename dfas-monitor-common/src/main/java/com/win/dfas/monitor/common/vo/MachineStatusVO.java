package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MachineStatusVO {

    private String id;
    private String state;
    private String ipAddress;
    private Integer cpuNum;
    private String memorySize;
    private String diskSize;
    private String cpuPer;
    private String memoryPer;
    private String diskPer;

}
