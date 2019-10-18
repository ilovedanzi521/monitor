package com.win.dfas.monitor.common.entity;

import com.win.dfas.monitor.common.vo.MicroServiceReqVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MicroServiceEntity {

    private Long id;
    private String microServiceName;
    private String microServiceAlias;

}
