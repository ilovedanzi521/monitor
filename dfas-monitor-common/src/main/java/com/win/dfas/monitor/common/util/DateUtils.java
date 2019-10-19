/****************************************************
 * 创建人：  @author wangyaoheng
 * 创建时间: 2019-08-19/13:43
 * 项目名称:  ibt-connector
 * 文件名称: DateUtil.java
 * 文件描述: 时间转换
 * 公司名称: 深圳市赢时胜信息技术有限公司
 *
 * All rights Reserved, Designed By 深圳市赢时胜信息技术有限公司
 * @Copyright:2016-2019
 *
 ********************************************************/
package com.win.dfas.monitor.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 时间转换
 * 包名称：com.win.dfas.monitor.common.util
 * 类名称：DateUtils
 * 类描述：时间转换
 * 创建人：@author wangyaoheng
 * 创建时间：2019-08-19/13:43
 */
public final class DateUtils {

	/** 开始时分秒 */
	public static final String BEGIN_TIME = "000000";

	/**
	 * 以秒为标准时间的毫秒数
	 */
	public static final long MILLIS_PER_SECOND = 1000;
	/**
	 *以分钟为标准时间的毫秒数
	 */
	public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
	/**
	 *以小时为标准时间的毫秒数
	 */
	public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
	/**
	 * 以天为标准时间的毫秒数
	 */
	public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

	public static final String PURE_DATETIME_MS_PATTERN = "yyyyMMdd-HH:mm:ss.SSS";

	/**
	 * 获取当前日期
	 * @return
	 */
	public static Integer getCurrentDateByIntegerFormat() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		return Integer.valueOf(dateString);
	}

	public static String getCurrentDateTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(currentTime);
	}

	public static String getDateTimeByMillis(Long time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(time);
		return simpleDateFormat.format(date);
	}

	/**
	 * double 转  Date 时间
	 * @param dDate
	 */
	public static String doubleToDate(double dDate) {
		DecimalFormat df = new DecimalFormat("#.000");
		String strDate = df.format(dDate);
		Date date = new Date(Long.valueOf(strDate.replace(".", "")));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}

	/**
	 * double 转  Date 时间
	 * @param dDate
	 */
	public static String doubleToDateOnlyHourMinute(double dDate) {
		DecimalFormat df = new DecimalFormat("#.000");
		String strDate = df.format(dDate);
		Date date = new Date(Long.valueOf(strDate.replace(".", "")));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		return simpleDateFormat.format(date);
	}

	public static String getCurrentHour(){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH");
		return formatter.format(currentTime);
	}

	/**
	 * 获取当前字符串日期
	 * @return
	 */
	public static String getCurrentDateByStringFormat() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(currentTime);
	}

	public static String getEndTime() {
		Date date = new Date();
		long timeStamp = date.getTime();
		String str = String.valueOf(timeStamp);
		str = str.substring(0, str.length() - 3) + "." + str.substring(str.length() - 3);
		return str;
	}

	public static String getStartTime() {
		Date date = new Date();
		date.setTime(date.getTime() - 24 * 60 * 60 * 1000);// 减去5分钟/小时以后的时间
		//System.out.println(sdf.format(date));// 再将该时间转换成字符串格式
		long timeStamp = date.getTime();
		//System.out.println(timeStamp);
		String str = String.valueOf(timeStamp);
		str = str.substring(0, str.length() - 3) + "." + str.substring(str.length() - 3);
		return str;
	}

	/**
	 * 获取当前字符串日期
	 * @return
	 */
	public static String getCurrentMonthDayByStringFormat() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
		return formatter.format(currentTime);
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static Integer getCurrentTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		String dateString = formatter.format(currentTime);
		return Integer.valueOf(dateString);
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getCurrentHourMinute() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		return formatter.format(currentTime);
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getCurrentHourMinuteSecond() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(currentTime);
	}

	/**
	 * 日期格式化：yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式化：yyyy-MM-dd
	 */
	public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	/**
	 * 一天的秒数
	 */
	public static final int ONE_DAY_SECONDS = 24 * 60 * 60;

	/**
	 * 一小时的秒数
	 */
	public static final int ONE_HOUR_SECONDS = 60 * 60;

	/**
	 * 一分钟的秒数
	 */
	public static final int ONE_MINUTE_SECONDS = 60;

	public static String YYYY = "yyyy";

	public static String YYYY_MM = "yyyy-MM";

	public static String YYYY_MM_DD = "yyyy-MM-dd";

	public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String CURR_DATE_MIN_SUFFIX = " 00:00:00";

	public static final String CURR_DATE_MAX_SUFFIX = " 23:59:59";

	public static Date toDate(long time) {
		Date dt = new Date(time);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dt;
	}

	public static Date toDate(String dateStr, String format) {
		Date d = null;
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			formater.setLenient(false);
			d = formater.parse(dateStr);
		} catch (Exception e) {
			// log.error(e);
			d = null;
		}
		return d;
	}

	/**
	 * 返回日期的年
	 *
	 * @param dateStr 日期字符串
	 *
	 * @return int
	 */
	public static int getYear(String dateStr) {
		if (isYYMMDD(dateStr)) {
			dateStr += " 00:00:00";
		}
		Date date = toDate(dateStr, DATE_FORMAT_YMDHMS);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static String toString(Date time) {
		return toString(time, DATE_FORMAT_YMDHMS);
	}

	/**
	 * 获取当前时间的指定格式
	 * @param format
	 * @return
	 */
	public static String getCurrDate(String format) {
		return toString(new Date(), format);
	}

	/**
	 * 获取指定时间到当前的时间跨度
	 * @return x天x小时x分钟x秒
	 */
	public static String toCurrentTime(Date startDate) {

		long intervalSeconds = getTimeInterval2Now(startDate) / 1000;

		StringBuilder returnVal = new StringBuilder("");
		if (!(intervalSeconds > 0)) {
			return returnVal.append("0秒").toString();
		}

		long days = intervalSeconds / ONE_DAY_SECONDS;
		long hours = (intervalSeconds % ONE_DAY_SECONDS) / (ONE_HOUR_SECONDS);
		long minutes = ((intervalSeconds % ONE_DAY_SECONDS) % (ONE_HOUR_SECONDS)) / ONE_MINUTE_SECONDS;
		long seconds = (((intervalSeconds % ONE_DAY_SECONDS) % (ONE_HOUR_SECONDS)) % ONE_MINUTE_SECONDS) % ONE_MINUTE_SECONDS;

		if (days > 0) {
			returnVal.append(days + "天");
		}
		if (hours > 0) {
			returnVal.append(hours + "小时");
		}
		if (minutes > 0) {
			returnVal.append(minutes + "分钟");
		}
		if (seconds > 0) {
			returnVal.append(seconds + "秒");
		}

		return returnVal.toString();
	}

	/**
	 * 获取当前时间 前多少秒的时间
	 * @param seconds
	 * @return
	 */
	public static Date fixBeforeDate(long seconds) {

		return new Date(System.currentTimeMillis() - seconds * 1000);
	}

	private static boolean isYYMMDD(String date) {
		Pattern p = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
		return p.matcher(date).matches();
	}

	/**
	 * 获取当前Date型日期
	 *
	 * @return Date() 当前日期
	 */
	public static Date getNowDate() {
		return new Date();
	}

	/**
	 * 获取当前日期, 默认格式为yyyy-MM-dd
	 *
	 * @return String
	 */
	public static String getDate() {
		return dateTimeNow(YYYY_MM_DD);
	}

	public static final String getTime() {
		return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
	}

	public static final String dateTimeNow() {
		return dateTimeNow(YYYYMMDDHHMMSS);
	}

	public static final String dateTimeNow(final String format) {
		return parseDateToStr(format, new Date());
	}

	public static final String dateTime(final Date date) {
		return parseDateToStr(YYYY_MM_DD, date);
	}

	public static final String parseDateToStr(final String format, final Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	public static final Date dateTime(final String format, final String ts) {
		try {
			return new SimpleDateFormat(format).parse(ts);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 日期路径 即年/月/日 如2018/08/08
	 */
	public static final String datePath() {
		Date now = new Date();
		return DateFormatUtils.format(now, "yyyy/MM/dd");
	}

	/**
	 * 日期路径 即年/月/日 如20180808
	 */
	public static final String dateTime() {
		Date now = new Date();
		return DateFormatUtils.format(now, "yyyyMMdd");
	}

	public static Date toDate(String dateStr) {

		Date d = null;
		SimpleDateFormat formater = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		try {
			formater.setLenient(false);
			d = formater.parse(dateStr);
		} catch (Exception e) {
			d = null;
		}
		formater = null;
		return d;
	}

	/**
	 * 获取当前时间的指定格式
	 * @return
	 */
	public static String getCurrDate() {

		return toString(new Date(), YYYY_MM_DD);
	}

	/**
	 * 获取当天最小时间
	 * @return
	 */
	public static String getCurrDateMinTime() {
		return getCurrDate() + CURR_DATE_MIN_SUFFIX;
	}

	/**
	 * 获取当天最大时间
	 * @return
	 */
	public static String getCurrDateMaxTime() {
		return getCurrDate() + CURR_DATE_MAX_SUFFIX;
	}

	/**
	 * 获取当前时间的yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 */
	public static String getCurrTime() {

		return toString(new Date(), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 把日期转换为字符串
	 *
	 * @param date
	 * @return
	 */
	public static String toString(Date date, String format) {

		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			result = formater.format(date);
		} catch (Exception e) {
		}
		formater = null;
		return result;
	}

	public static long getTimeInterval2Now(Date fromTime) {

		return System.currentTimeMillis() - fromTime.getTime();
	}

	/**
	 * 获取当前时间 前多少毫秒的时间
	 *
	 * @param seconds
	 * @return
	 */
	public static Date getBeforeMilliDate(long seconds) {

		return new Date(System.currentTimeMillis() - seconds);
	}

	/**
	 * 获取当前时间 后多少毫秒的时间
	 *
	 * @param seconds
	 * @return
	 */
	public static Date getAfterMilliDate(long seconds) {

		return new Date(System.currentTimeMillis() + seconds);
	}

	/**
	 * 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
	 *
	 * @param date1
	 *            Date
	 * @param date2
	 *            Date
	 * @return long
	 */
	public static long dayDiff(Date date1, Date date2) {

		return (date2.getTime() - date1.getTime()) / (1000 * 24 * 60 * 60);
	}

	/**
	 * 计算两个日期相差的毫秒数
	 *
	 * @param date1
	 *            Date
	 * @param date2
	 *            Date
	 * @return long
	 */
	public static long longDiff(Date date1, Date date2) {

		return (date2.getTime() - date1.getTime());
	}

	public static int calLastedTime(Date date1, Date date2) {

		long a = date1.getTime();
		long b = date2.getTime();
		int c = (int) ((b - a) / 1000);
		return c;
	}

	/**
	 * 获取零点时间
	 *
	 * @return
	 */
	public static String getZeroDate() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();
		return toString(zero);
	}

	/**
	 * 获取当天0点或者23:59:59时间
	 *
	 * @param date
	 * @param flag
	 *            为true表示获取0点，否则获取23:59:59
	 * @return
	 */
	public static Date getDayDates(Date date, boolean flag) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		// 时分秒（毫秒数）
		long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
		// 凌晨00:00:00
		cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

		if (flag) {
			return cal.getTime();
		} else {
			// 凌晨23:59:59
			cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
		}
		return cal.getTime();
	}

	public static String formatDate(Date date, String format) {

		return new SimpleDateFormat(format).format(date);
	}

	public static int compare_date(String DATE1, String DATE2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 增加时间的分钟数
	 *
	 * @param date
	 * @param amount 长度
	 * @return
	 */
	public static Date addMinutes(final Date date, final int amount) {
		return add(date, Calendar.MINUTE, amount);
	}

	/**
	 * 增加时间的某个域的大小
	 * @param date
	 * @param calendarField 增加的时间域(时，分，秒等)
	 * @param amount 增加的数量
	 * @return
	 */
	public static Date add(final Date date, final int calendarField, final int amount) {

		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	/**
	 * 获取一个月之前的时间
	 * @return
	 */
	public static String getBeforeMonthTime() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		return toString(m, YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * @获取昨天日期
	 * @return
	 */
	public static String getYesterdayByCalendar() {
		Calendar calendar = Calendar.getInstance();
		System.out.println(Calendar.DATE);
		calendar.add(Calendar.DATE, -1);
		Date time = calendar.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @获取本周的第一天
	 * @return String
	 */
	public static String getWeekStart() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
		cal.add(Calendar.WEEK_OF_MONTH, 0);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @获取上周的第一天
	 * @return String
	 */
	public static String getLastWeekStart() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
		cal.add(Calendar.DATE, -1 * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @获取本周的最后一天
	 * @return String
	 */
	public static String getWeekEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
		cal.add(Calendar.DAY_OF_WEEK, 1);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @获取上周的最后一天
	 * @return String
	 */
	public static String getLastWeekEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
		cal.add(Calendar.DATE, -1 * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @获取本月开始日期
	 * @return String
	 */
	public static String getMonthStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @获取上月开始日期
	 * @return String
	 */
	public static String getFirstDayofMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date time = calendar.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @获取上月結束日期
	 * @return String
	 */
	public static String getLastDayofMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date time = calendar.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @获取过去第几天的日期
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past, String fmat) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat(fmat);
		String result = format.format(today);
		return result;
	}

	/**
	 * @获取前多少周的第一天
	 * @return String
	 */
	public static String getPastWeekStart(int past) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
		cal.add(Calendar.DATE, -1 * 7 * past);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @获取前多少周的最后一天
	 * @return String
	 */
	public static String getPastWeekEnd(int past) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
		cal.add(Calendar.DATE, -1 * 7 * past);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @获取前n月开始日期
	 * @return String
	 */
	public static String getPastMonthStart(int past) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1 * past);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date time = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @获取前year开始日期
	 * @return String
	 */
	public static String getPastYearStartTime(int past) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -1 * past);// 拨回去年
		cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));// 最小一天
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * @获取前多少月結束日期
	 * @return String
	 */
	public static String getPastEndDayofMonth(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1 * past);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date time = calendar.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @获取前多少年結束日期
	 * @return String
	 */
	public static String getPastYearEndTime(int past) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -1 * past);// 拨回去年
		cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));// 最后一天
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	public static void main(String args[]) {
		System.out.println(DateUtils.getPastDate(7, "yyyyMMdd"));
	}

	/**
	 * @当前季度的开始时间，即2012-01-01
	 * @return
	 */
	public static String getCurrentQuarterStartTimeByFixedDate(String fixedDate) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(simpleDateFormat.parse(fixedDate));
			int currentMonth = c.get(Calendar.MONTH) + 1;
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 0);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 3);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 6);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 9);
			}
			c.set(Calendar.DATE, 1);
			Date time = c.getTime();
			return new SimpleDateFormat("yyyy-MM-dd").format(time);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * @当前季度的结束时间，即2012-03-31
	 * @return
	 */
	public static String getCurrentQuarterEndTimeByFixedDate(String fixedDate) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(simpleDateFormat.parse(fixedDate));
			int currentMonth = c.get(Calendar.MONTH) + 1;
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 3);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 6);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 9);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 12);
			}
			c.set(Calendar.DATE, 0);
			Date time = c.getTime();
			return new SimpleDateFormat("yyyy-MM-dd").format(time);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * @上季度的结束时间，即2012-01-01
	 * @return
	 */
	public static String getLastQuarterStartTimeByFixedDate(String fixedDate) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(simpleDateFormat.parse(fixedDate));
			int currentMonth = c.get(Calendar.MONTH) + 1;
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 9);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 0);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 3);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 6);
			}
			c.set(Calendar.DATE, 1);
			Date time = c.getTime();
			return new SimpleDateFormat("yyyy-MM-dd").format(time);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * @上季度的结束时间，即2012-01-01
	 * @return
	 */
	public static String getLastQuarterEndTimeByFixedDate(String fixedDate) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(simpleDateFormat.parse(fixedDate));
			int currentMonth = c.get(Calendar.MONTH) + 1;
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 0);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 3);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 6);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 9);
			}
			c.set(Calendar.DATE, 0);
			Date time = c.getTime();
			return new SimpleDateFormat("yyyy-MM-dd").format(time);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * @当前季度的开始时间，即2012-01-1
	 * @return
	 */
	public static String getCurrentQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		if (currentMonth >= 1 && currentMonth <= 3) {
			c.set(Calendar.MONTH, 0);
		} else if (currentMonth >= 4 && currentMonth <= 6) {
			c.set(Calendar.MONTH, 3);
		} else if (currentMonth >= 7 && currentMonth <= 9) {
			c.set(Calendar.MONTH, 6);
		} else if (currentMonth >= 10 && currentMonth <= 12) {
			c.set(Calendar.MONTH, 9);
		}
		c.set(Calendar.DATE, 1);
		Date time = c.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @上季度的开始时间，即2012-01-1
	 * @return
	 */
	public static String getLastQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		if (currentMonth >= 1 && currentMonth <= 3) {
			c.set(Calendar.MONTH, 9);
		} else if (currentMonth >= 4 && currentMonth <= 6) {
			c.set(Calendar.MONTH, 0);
		} else if (currentMonth >= 7 && currentMonth <= 9) {
			c.set(Calendar.MONTH, 3);
		} else if (currentMonth >= 10 && currentMonth <= 12) {
			c.set(Calendar.MONTH, 6);
		}
		c.set(Calendar.DATE, 1);
		Date time = c.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @上季度的结束时间，即2012-01-1
	 * @return
	 */
	public static String getLastQuarterEndTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		if (currentMonth >= 1 && currentMonth <= 3) {
			c.set(Calendar.MONTH, 12);
		} else if (currentMonth >= 4 && currentMonth <= 6) {
			c.set(Calendar.MONTH, 3);
		} else if (currentMonth >= 7 && currentMonth <= 9) {
			c.set(Calendar.MONTH, 6);
		} else if (currentMonth >= 10 && currentMonth <= 12) {
			c.set(Calendar.MONTH, 9);
		}
		c.set(Calendar.DATE, 0);
		Date time = c.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}

	/**
	 * @当前年开始时间
	 * @return
	 */
	public static String getCurrentYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));// 最后一天
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	public static String getLastYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -1);// 拨回去年
		cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));// 最后一天
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	public static String getLastYearEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -1);// 拨回去年
		cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));// 最后一天
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 增加时间的天数
	 * @param dateStr 日期字符串
	 * @param days 增加的天数
	 * @return
	 */
	public static Date addDay(String dateStr, int days) {
		Date date = toDate(dateStr, DATE_FORMAT_YMD);
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

}