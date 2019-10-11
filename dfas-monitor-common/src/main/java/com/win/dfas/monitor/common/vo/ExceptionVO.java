package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExceptionVO {

    private String machineCpu;
    private String machineMemory;
    private String machineDisk;
    private String microServiceMemory;
    private String microServiceWarnLog;
    private String microServiceErrorLog;

}
