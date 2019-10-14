package com.win.dfas.monitor.common.vo;

import com.win.dfas.common.vo.BaseReqVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MicroServiceReqVO extends BaseReqVO {
	
    private String microServiceName;
    private String state;
    private String warn;
    private String error;
	
}
