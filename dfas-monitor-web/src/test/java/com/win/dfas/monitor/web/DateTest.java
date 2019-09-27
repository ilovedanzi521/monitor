package com.win.dfas.monitor.web;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.win.dfas.monitor.common.util.DateUtils;

public class DateTest {

	@Test
	public void test() throws ParseException {
		String s = "1569502775670";
		//String s = "1569500656042";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		String res = simpleDateFormat.format(date);
		System.out.println(res);
	}

	@Test
	public void test2() throws ParseException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));
		//获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
		long timeStamp = date.getTime();
		System.out.println(timeStamp);
		date.setTime(date.getTime() - 5 * 60 * 1000);// 减去4分钟以后的时间
		System.out.println(sdf.format(date));// 再将该时间转换成字符串格式
		timeStamp = date.getTime();
		System.out.println(timeStamp);
		String str = String.valueOf(timeStamp);
		str = str.substring(0, str.length() - 3) + "." + str.substring(str.length() - 3);
		System.out.println(str);
	}

	@Test
	public void changetoDouble() {
		try {
			double dDate = 1.569555696274E9;
			DecimalFormat df = new DecimalFormat("#.000");
			String strDate = df.format(dDate);
			Date date = new Date(Long.valueOf(strDate.replace(".", "")));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String strDate2 = simpleDateFormat.format(date);
	        System.out.println(strDate2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * double 转  Date 时间
	 * @param dVal
	 */
	public static String doubleToDate(double dVal) {
		Date oDate = new Date();
		@SuppressWarnings("deprecation")
		long localOffset = oDate.getTimezoneOffset() * 60000; //系统时区偏移 1900/1/1 到 1970/1/1 的 25569 天
		oDate.setTime((long) ((dVal - 25569) * 24 * 3600 * 1000 + localOffset));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  simpleDateFormat.format(oDate);
	}

	public static double changetoDouble3(String strDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Double etDay = 0.0;
		Double etTime = 0.0;
		try {
			Date date = df.parse(strDate);
			etDay = Double.parseDouble(date.getTime() / (1000 * 60 * 60 * 24) + "");
			etTime = date.getHours() / 24.0 + date.getMinutes() / (24.0 * 60) + date.getSeconds() / (24.0 * 60 * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etDay + etTime;
	}

	/**
     * double 转  Date 时间
     * @param dVal
     */
    public static Date DoubleToDate(Double dVal){
    	Date oDate = new Date();
        @SuppressWarnings("deprecation")
		long localOffset = oDate.getTimezoneOffset() * 60000; //系统时区偏移 1900/1/1 到 1970/1/1 的 25569 天
        oDate.setTime((long) ((dVal - 25569) * 24 * 3600 * 1000 + localOffset));
        
        return oDate;
	}

}
