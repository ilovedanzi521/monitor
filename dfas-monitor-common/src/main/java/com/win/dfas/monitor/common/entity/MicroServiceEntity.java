package com.win.dfas.monitor.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MicroServiceEntity {

    private String id;
    private String microServiceName;
    private String state;
    private String warn;
    private String error;

}
