package com.win.dfas.monitor.common.util;

import java.math.BigDecimal;
/**
 * 大数值处理工具类
 *
 * @author lj
 */
public class BigDecimalUtils {
    /**
     * @param addOne
     * @param addTwo
     * @return 高精度的加法运算
     */
    public static double bigDecimalAdd(double addOne, double addTwo) {
        //创建BigDecimal对象
        BigDecimal b1 = new BigDecimal(addOne);
        BigDecimal b2 = new BigDecimal(addTwo);
        return b1.add(b2).doubleValue();
    }

    /**
     * @param subOne
     * @param subTwo
     * @return 高精度运算减法
     */
    public static double bigDecimalSub(double subOne, double subTwo) {
        BigDecimal b1 = new BigDecimal(subOne);
        BigDecimal b2 = new BigDecimal(subTwo);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * @param mulOne
     * @param mulTwo
     * @return 高精度的乘法
     */
    public static double bigDecimalMul(double mulOne, double mulTwo) {
        BigDecimal b1 = new BigDecimal(mulOne);
        BigDecimal b2 = new BigDecimal(mulTwo);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * @param divOne
     * @param divTwo
     * @return 高精度除法
     */
    public static double bigDecimalDiv(double divOne, double divTwo) {
        BigDecimal b1 = new BigDecimal(divOne);
        BigDecimal b2 = new BigDecimal(divTwo);
        return b1.divide(b2).doubleValue();//这里可以设置保留精度，保留位数下面我们在细细介绍。
    }

    /**
     * @param roundDate
     * @param length    保留小数后的位数
     * @return 返回四舍五入后的参数
     */
    public static double bigDecimalRound(double roundDate, int length) {
        BigDecimal b1 = new BigDecimal(roundDate);
        BigDecimal b2 = new BigDecimal(1);
        //第三个参数是表示四舍五入操作，这里传入的参数需要时字符串，不然可能不对下面我在介绍。
        return b1.divide(b2, length, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * @param roundDate
     * @param length
     * @return 返回四舍五入后的参数
     */
    public static double bigDecimalStringRound(String roundDate, int length) {
        BigDecimal b1 = new BigDecimal(roundDate);
        BigDecimal b2 = new BigDecimal(1);
        return b1.divide(b2, length, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
