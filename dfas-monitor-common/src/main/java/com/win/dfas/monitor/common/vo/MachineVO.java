package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MachineVO {

    private String ip;
    private String state;
    private String balance;
    private String cpu;
    private String memory;
    private String disk;
    /** 资源明细页显示CPU信息 */
    private  String cpuInfo;
    /** 资源明细页显示磁盘信息 */
    private  String diskInfo;
    /** 资源明细页显示内存信息 */
    private  String memoryInfo;
    /** 资源明细页显示负载信息 */
    private  String balanceInfo;

}
