package com.win.dfas.monitor.common.vo.alert;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AlertVO {

    private String status;
    private LabelVO labels;
    private AnnotationVO annotations;


}
