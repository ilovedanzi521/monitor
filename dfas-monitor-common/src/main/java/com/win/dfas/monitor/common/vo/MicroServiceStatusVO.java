package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MicroServiceStatusVO {

    private String id;
    private String state;
    private String name;
    private Integer warn;
    private Integer error;

}
