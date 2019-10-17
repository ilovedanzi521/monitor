package com.win.dfas.monitor.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MicroServiceEntity {

    private Long id;
    private String microServiceName;
    private String microServiceAlias;
    private String instanceId;
    private String hostName;
    private String app;
    private String ipAddr;

}
