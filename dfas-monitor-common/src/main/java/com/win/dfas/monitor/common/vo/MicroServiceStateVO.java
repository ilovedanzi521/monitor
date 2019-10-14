package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MicroServiceStateVO {

    private String id;
    private String state;
    private String microServiceName;
    private Integer warn;
    private Integer error;

}
