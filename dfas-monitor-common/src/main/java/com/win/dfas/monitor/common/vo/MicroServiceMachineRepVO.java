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
    private String ipAddr;

    /**
     * 端口
     */
    private String port;

    /**
     * 状态
     */
    private String state="DOWN";

    /**
     * 负载
     */
    private String balance="-";

    /**
     * jvm
     */
    private String jvm="-";

    /**
     * 告警数
     */
    private String warn="-";

    /**
     * 错误数
     */
    private String error="-";

}
