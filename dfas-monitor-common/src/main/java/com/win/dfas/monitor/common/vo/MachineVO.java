package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MachineVO {

    private String ip;
    private String status;
    private String balance;
    private String cpu;
    private String memory;
    private String disk;

}
