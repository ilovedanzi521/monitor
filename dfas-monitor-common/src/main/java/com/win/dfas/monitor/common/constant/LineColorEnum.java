package com.win.dfas.monitor.common.constant;

public enum LineColorEnum {

    green("#33CC33"),
    red("#FF4D4D"),
    blue("#00BAF3");

    private String color;

    LineColorEnum(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
