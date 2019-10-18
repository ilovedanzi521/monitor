package com.win.dfas.monitor.common.constant;

public enum  StatusEnum {

    OFFLINE("0"),

    EXCEPTION("1"),

    WARN("2"),

    ONLINE("3");

    private String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
