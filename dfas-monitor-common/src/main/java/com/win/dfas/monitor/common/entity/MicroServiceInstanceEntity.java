package com.win.dfas.monitor.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MicroServiceInstanceEntity {

    private Long id;
    private Long serviceId;
    private String instanceId;
    private String hostName;
    private String app;
    private String ipAddr;

}
