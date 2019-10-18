package com.win.dfas.monitor.common.vo;

import com.win.dfas.common.vo.BaseRepVO;
import com.win.dfas.monitor.common.constant.MonitorConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MicroServiceRepVO extends BaseRepVO {

    private String microServiceName;
    private String microServiceAlias;
    private String ipAddress;
    private String state = MonitorConstants.DEFAULT_DISPLAY_SYMBOL;
    private String warn = MonitorConstants.DEFAULT_DISPLAY_SYMBOL;
    private String error = MonitorConstants.DEFAULT_DISPLAY_SYMBOL;

}
