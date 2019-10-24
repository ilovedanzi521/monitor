package com.win.dfas.monitor.common.vo.alert;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AlertMsgVO {

    private String receiver;
    private String status;
    private List<AlertVO> alerts;


}
