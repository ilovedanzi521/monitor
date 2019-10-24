package com.win.dfas.monitor.common.dto.microservice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class ApplicationInstance {

    private String instanceId;
    private String hostName;
    private String app;
    private String ipAddr;
    private String status;
    private String overriddenStatus;
    private Object port;
    private Object securePort;
    private Long countryId;
    private Object dataCenterInfo;
    private Object leaseInfo;
    private Object metadata;
    private String homePageUrl;
    private String statusPageUrl;
    private String healthCheckUrl;
    private String vipAddress;
    private String secureVipAddress;
    private String isCoordinatingDiscoveryServer;
    private String lastUpdatedTimestamp;
    private String lastDirtyTimestamp;
    private String actionType;

}
