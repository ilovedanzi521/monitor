package com.win.dfas.monitor.common.vo.alert;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LabelVO {
    private String alertname;
    private String instance;
    private String job;
    private String severity;
}
