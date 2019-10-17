package com.win.dfas.monitor.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MicroServiceApplicationsDTO {

    private String versions__delta;

    private String apps__hashcode;

    private List<MicroServiceApplicationDTO> application;

}
