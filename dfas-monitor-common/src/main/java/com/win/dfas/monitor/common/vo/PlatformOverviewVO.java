package com.win.dfas.monitor.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlatformOverviewVO {

    private String totalNode;
    private String totalHttpRequest;
    private String totalMicroService;
    private String qps;

}
