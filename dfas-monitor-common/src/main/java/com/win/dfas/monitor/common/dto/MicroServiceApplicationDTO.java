package com.win.dfas.monitor.common.dto;

import com.win.dfas.monitor.common.dto.microservice.ApplicationInstance;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MicroServiceApplicationDTO {

    private String name;
    private List<ApplicationInstance> instance;

}
