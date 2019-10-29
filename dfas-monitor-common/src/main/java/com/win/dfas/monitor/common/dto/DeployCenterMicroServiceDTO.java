package com.win.dfas.monitor.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DeployCenterMicroServiceDTO {

    private  String  microServiceName;
    private  String  microServiceAlias;
    private List<DeployCenterMicroServiceInstanceDTO> instanceList;

}
