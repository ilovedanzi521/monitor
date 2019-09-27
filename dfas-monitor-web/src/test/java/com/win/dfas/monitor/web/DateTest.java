package com.win.dfas.monitor.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateTest {

	@Test
	public void test() throws ParseException {
		String s ="1569502775670";
		//String s = "1569500656042";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		String res = simpleDateFormat.format(date);
		System.out.println(res);
	}

	@Test
	public void test2() throws ParseException {
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));
		//获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
		long timeStamp = date.getTime();
		System.out.println(timeStamp);
		date.setTime(date.getTime() - 5 * 60 * 1000);// 减去4分钟以后的时间
		System.out.println(sdf.format(date));// 再将该时间转换成字符串格式
		timeStamp = date.getTime();
		System.out.println(timeStamp);
		String str=String.valueOf(timeStamp);
		str = str.substring(0,str.length()-3)+"."+str.substring(str.length()-3);
		System.out.println(str);
	}

}
