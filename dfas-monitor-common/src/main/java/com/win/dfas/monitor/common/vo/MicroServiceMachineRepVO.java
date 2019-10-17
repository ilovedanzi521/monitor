package com.win.dfas.monitor.common.vo;

import com.win.dfas.common.vo.BaseRepVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MicroServiceMachineRepVO extends BaseRepVO {

    /**
     * 实例ID
     */
    private String instanceId;

    /**
     * IP地址
     */
    public String ipAddr;

    /**
     * 状态
     */
    public String state="DOWN";

    /**
     * 负载
     */
    public String balance="-";

    /**
     * jvm
     */
    public String jvm="-";

    /**
     * 告警数
     */
    public String warn="-";

    /**
     * 错误数
     */
    public String error="-";

}
