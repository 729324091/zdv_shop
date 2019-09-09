package com.zdv.shop.common.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * Description:日期时间操作的工具类
 * 
 * @author administrator
 */
public class DateUtil {
	/** 日期格式(yyyy-MM-dd) */
	public static final String yyyy_MM_dd_EN = "yyyy-MM-dd";
	/** 日期格式(yyyy/MM/dd) */
	public static final String yyyy_MM_dd_decline = "yyyy/MM/dd";
	/** 日期格式(yyyyMMdd) */
	public static final String yyyyMMdd_EN = "yyyyMMdd";
	/** 日期格式(yyyy-MM) */
	public static final String yyyy_MM_EN = "yyyy-MM";
	/** 日期格式(yyyyMM) */
	public static final String yyyyMM_EN = "yyyyMM";
	/** 日期格式(yyyy-MM-dd HH:mm:ss) */
	public static final String yyyy_MM_dd_HH_mm_ss_EN = "yyyy-MM-dd HH:mm:ss";
	/** 日期格式(yyyy-MM-dd HH:mm:ss.S) */
	public static final String yyyy_MM_dd_HH_mm_ss_S_EN = "yyyy-MM-dd HH:mm:ss.S";
	/** 日期格式(yyyyMMddHHmmss) */
	public static final String yyyyMMddHHmmss_EN = "yyyyMMddHHmmss";
	/** 日期格式(yyyy年MM月dd日) */
	public static final String yyyy_MM_dd_CN = "yyyy年MM月dd日";
	/** 日期格式(yyyy年MM月dd日HH时mm分ss秒) */
	public static final String yyyy_MM_dd_HH_mm_ss_CN = "yyyy年MM月dd日HH时mm分ss秒";
	/** 日期格式(yyyy年MM月dd日HH时mm分) */
	public static final String yyyy_MM_dd_HH_mm_CN = "yyyy年MM月dd日HH时mm分";
	/** 北京boss订购接口报文头日期格式 */
	public static final String BJBOSS_DATE = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	/** 日期格式(HH:mm:ss) */
	public static final String HH_mm_ss_EN = "HH:mm:ss";
	/** DateFormat缓存 */
	private static Map<String, DateFormat> dateFormatMap = new HashMap<String, DateFormat>();

	/**
	 * 获取DateFormat
	 * @param dateTimeStr
	 * @param formatStr
	 * @return
	 */
	public static DateFormat getDateFormat(String formatStr) {
		DateFormat df = dateFormatMap.get(formatStr);
		if (df == null) {
			df = new SimpleDateFormat(formatStr);
			dateFormatMap.put(formatStr, df);
		}
		return df;
	}

	public static Date getDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 按照默认formatStr的格式，转化dateTimeStr为Date类型 dateTimeStr必须是formatStr的形式
	 * @param dateTimeStr
	 * @param formatStr
	 * @return
	 */
	public static Date getDate(String dateTimeStr, String formatStr) {
		try {
			if (dateTimeStr == null || dateTimeStr.equals("")) {
				return null;
			}
			DateFormat sdf = DateUtil.getDateFormat(formatStr);
			return sdf.parse(dateTimeStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 转化dateTimeStr为Date类型
	 * @param dateTimeStr
	 * @param formatStr
	 * @return
	 */
	public static Date convertDate(String dateTimeStr) {
		try {
			if (dateTimeStr == null || dateTimeStr.equals("")) {
				return null;
			}
			DateFormat sdf = DateUtil.getDateFormat(yyyy_MM_dd_EN);
			Date d = sdf.parse(dateTimeStr);
			return d;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按照默认显示日期时间的格式"yyyy-MM-dd"，转化dateTimeStr为Date类型
	 * dateTimeStr必须是"yyyy-MM-dd"的形式
	 * @param dateTimeStr
	 * @return
	 */
	public static Date getDate(String dateTimeStr) {
		return getDate(dateTimeStr, yyyy_MM_dd_EN);
	}

	/**
	 * 将YYYYMMDD转换成Date日期
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public static Date transferDate(String date) throws Exception {
		if (date == null || date.length() < 1)
			return null;

		if (date.length() != 8)
			throw new Exception("日期格式错误");
		String con = "-";

		String yyyy = date.substring(0, 4);
		String mm = date.substring(4, 6);
		String dd = date.substring(6, 8);

		int month = Integer.parseInt(mm);
		int day = Integer.parseInt(dd);
		if (month < 1 || month > 12 || day < 1 || day > 31)
			throw new Exception("日期格式错误");

		String str = yyyy + con + mm + con + dd;
		return DateUtil.getDate(str, DateUtil.yyyy_MM_dd_EN);
	}

	/**
	 * 将Date转换成字符串“yyyy-mm-dd hh:mm:ss”的字符串
	 * @param date
	 * @return
	 */
	public static String dateToDateString(Date date) {
		return dateToDateString(date, yyyy_MM_dd_HH_mm_ss_EN);
	}

	/**
	 * 将Date转换成字符串“yyyymmddhhmmss”的字符串
	 * @param date
	 * @return
	 */
	public static String dateToDateFullString(Date date) {
		if (null == date)
			return null;
		else
			return dateToDateString(date, yyyyMMddHHmmss_EN);
	}

	/**
	 * 将Date转换成formatStr格式的字符串
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateToDateString(Date date, String formatStr) {
		DateFormat df = getDateFormat(formatStr);
		return df.format(date);
	}

	/**
	 * 将String转换成formatStr格式的字符串
	 * @param dateTime
	 * @param formatStr1
	 * @param formatStr2
	 * @return
	 */
	public static String stringToDateString(String date, String formatStr1, String formatStr2) {
		Date d = getDate(date, formatStr1);
		DateFormat df = getDateFormat(formatStr2);
		return df.format(d);
	}

	/**
	 * 获取当前日期yyyy-MM-dd的形式
	 * @return
	 */
	public static String getCurDate() {
		return dateToDateString(new Date(), yyyy_MM_dd_EN);
	}

	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getCurDate(String formatStr) {
		return dateToDateString(new Date(), formatStr);
	}

	/**
	 * 获取当前日期yyyy年MM月dd日的形式
	 * @return
	 */
	public static String getCurCNDate() {
		return dateToDateString(new Date(), yyyy_MM_dd_CN);
	}

	/**
	 * 获取当前日期时间yyyy-MM-dd HH:mm:ss的形式
	 * @return
	 */
	public static String getCurDateTime() {
		return dateToDateString(new Date(), yyyy_MM_dd_HH_mm_ss_EN);
	}

	/**
	 * 获取当前日期时间yyyy年MM月dd日HH时mm分ss秒的形式
	 * @return
	 */
	public static String getCurZhCNDateTime() {
		return dateToDateString(new Date(), yyyy_MM_dd_HH_mm_ss_CN);
	}

	/**
	 * 比较两个"yyyy-MM-dd HH:mm:ss"格式的日期，之间相差多少毫秒,time2-time1
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long compareDateStr(String time1, String time2) {
		Date d1 = getDate(time1);
		Date d2 = getDate(time2);
		return d2.getTime() - d1.getTime();
	}
	
	/**
	 * 比较任意格式时间相差毫秒数
	 * @param time1
	 * @param time2
	 * @param format
	 * @return
	 */
	public static long compareDateStr(String time1, String time2, String format){
		Date d1 = getDate(time1, format);
		Date d2 = getDate(time2, format);
		return d2.getTime() - d1.getTime();
	}
	
	/**
	 * 比较起始时间与当前时间相差毫秒数
	 * @param time
	 * @param format
	 * @return
	 */
	public static long compareDateNow(String time, String format){
		Date date = getDate(time, format);
		return new Date().getTime() - date.getTime();
	}

	/**
	 * 比较两个"yyyy-MM-dd HH:mm:ss"格式的日期，之间相差多少毫秒,time2-time1
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long compareDateStr(Date time1, Date time2) {
		return time2.getTime() - time1.getTime();
	}

	/**
	 * nows时间大于date时间 为true
	 * @param nows
	 * @param date
	 * @return
	 */
	public static boolean isTimeBefor(Date nows, Date date) {
		long hous = nows.getTime() - date.getTime();
		if (hous > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将小时数换算成返回以毫秒为单位的时间
	 * @param hours
	 * @return
	 */
	public static long getMicroSec(BigDecimal hours) {
		BigDecimal bd;
		bd = hours.multiply(new BigDecimal(3600 * 1000));
		return bd.longValue();
	}

	/**
	 * 获取当前日期years年后的一个(formatStr)的字符串
	 * @param months
	 * @param formatStr
	 * @return
	 */
	public static String getDateStringOfYear(int years, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.YEAR, years);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取当前日期mon月后的一个(formatStr)的字符串
	 * @param months
	 * @param formatStr
	 * @return
	 */
	public static String getDateStringOfMon(int months, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.MONTH, months);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取当前日期days天后的一个(formatStr)的字符串
	 * @param days
	 * @param formatStr
	 * @return
	 */
	public static String getDateStringOfDay(int days, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.DATE, days);
		return dateToDateString(now.getTime(), formatStr);
	}
	
	/**
	 * 判断日期是否是今天
	 * @param date
	 * @return
	 */
	public static int theDateIsToday(String date, String format){
		String theDate = stringToDateString(date, format, yyyyMMdd_EN);
		String today = getDateStringOfDay(0, yyyyMMdd_EN);
		if(theDate.equals(today)){
			return 1;
		}else{
			return 0;
		}
	}

	/**
	 * 获取当前日期hours小时后的一个(formatStr)的字符串
	 * @param hours
	 * @param formatStr
	 * @return
	 */
	public static String getDateStringOfHour(int hours, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.HOUR_OF_DAY, hours);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取指定日期mon月后的一个(formatStr)的字符串
	 * @param date
	 * @param mins
	 * @param formatStr
	 * @return
	 */
	public static String getDateOfMon(String date, int mon, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(DateUtil.getDate(date, formatStr));
		now.add(Calendar.MONTH, mon);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取指定日期day天后的一个(formatStr)的字符串
	 * @param date
	 * @param mins
	 * @param formatStr
	 * @return
	 */
	public static String getDateOfDay(String date, int day, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(DateUtil.getDate(date, formatStr));
		now.add(Calendar.DATE, day);
		return dateToDateString(now.getTime(), formatStr);
	}
	
	public static Date getDate(Date beginDate,int ds){
		if(ds == 0) return new Date();
		try {
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - ds);
			Date endDate = dft.parse(dft.format(date.getTime()));
			return endDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static String getAfterNDays(Date date, int n, String formateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formateStr);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, n);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 获取指定日期mins分钟后的一个(formatStr)的字符串
	 * @param date
	 * @param mins
	 * @param formatStr
	 * @return
	 */
	public static String getDateOfMin(String date, int mins, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(DateUtil.getDate(date, formatStr));
		now.add(Calendar.SECOND, mins * 60);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取指定日期mins分钟后的一个日期
	 * @param date
	 * @param mins
	 * @param formatStr
	 * @return
	 */
	public static Date getDateOfMin(Date date, int mins) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(date);
		now.add(Calendar.SECOND, mins * 60);
		return now.getTime();
	}

	/**
	 * 获取当前日期mins分钟后的一个(formatStr)的字符串
	 * @param mins
	 * @param formatStr
	 * @return
	 */
	public static String getDateStringOfMin(int mins, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.MINUTE, mins);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获取当前日期mins分钟后的一个日期
	 * @param mins
	 * @return
	 */
	public static Date getDateOfMin(int mins) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.MINUTE, mins);
		return now.getTime();
	}

	/**
	 * 获取当前日期sec秒后的一个(formatStr)的字符串
	 * @param sec
	 * @param formatStr
	 * @return
	 */
	public static String getDateStringOfSec(int sec, String formatStr) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(new Date());
		now.add(Calendar.SECOND, sec);
		return dateToDateString(now.getTime(), formatStr);
	}

	/**
	 * 获得指定日期月份的天数
	 * @return
	 */
	public static int getMonthDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);

	}

	/**
	 * 获得系统当前月份的天数
	 * @return
	 */
	public static int getCurentMonthDay() {
		Date date = Calendar.getInstance().getTime();
		return getMonthDay(date);
	}

	/**
	 * 获得指定日期月份的天数 yyyy-mm-dd
	 * @return
	 */
	public static int getMonthDay(String date) {
		Date strDate = getDate(date, yyyy_MM_dd_EN);
		return getMonthDay(strDate);
	}

	/**
	 * 获取19xx,20xx形式的年
	 * @param d
	 * @return
	 */
	public static int getYear(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.YEAR);
	}

	/**
	 * 获取月份，1-12月
	 * @param d
	 * @return
	 */
	public static int getMonth(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取xxxx-xx-xx的日
	 * @param d
	 * @return
	 */
	public static int getDay(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取Date中的小时(24小时)
	 * @param d
	 * @return
	 */
	public static int getHour(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取Date中的分钟
	 * @param d
	 * @return
	 */
	public static int getMin(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.MINUTE);
	}

	/**
	 * 获取Date中的秒
	 * @param d
	 * @return
	 */
	public static int getSecond(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.SECOND);
	}

	/**
	 * 得到本周周一
	 * @return yyyy-MM-dd
	 */
	public static String getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return dateToDateString(c.getTime(), yyyy_MM_dd_EN);
	}

	/**
	 * 得到本周周日
	 * @return yyyy-MM-dd
	 */
	public static String getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		return dateToDateString(c.getTime());
	}

	/**
	 * 得到本周周(*)
	 * @return yyyy-MM-dd
	 */
	public static String getDayOfThisWeek(int num) {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + num);
		return dateToDateString(c.getTime(), yyyy_MM_dd_EN);
	}

	/**
	 * 得到本月指定天
	 * @return yyyy-MM-dd
	 */
	public static String getDayOfThisMoon(String num) {
		String date = dateToDateString(new Date(), yyyy_MM_EN);
		date = date + "-" + num;
		return date;
	}

	/**
	 * 获取两个日期相差的天数
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long getQuotByDays(String beginDate, String endDate) {
		long quot = 0;
		DateFormat df = getDateFormat(yyyy_MM_dd_EN);
		try {
			Date d1 = df.parse(beginDate);
			Date d2 = df.parse(endDate);
			quot = d2.getTime() - d1.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return quot;
	}

	/**
	 * 根据日期追加的天数，得到一个新日期
	 * @param date
	 * @param days
	 * @return
	 */
	public static String getDateAddDay(String date, int days, String format) {
		DateFormat df = getDateFormat(format);
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(df.parse(date));
			cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + days);

			date = df.format(cal.getTime());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * 获取当前月的最后一天
	 * @return
	 */
	public static Date getLastDayOfCurrMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 0);

		return cal.getTime();
	}

	/**
	 * 根据日期追加的天数，得到一个新日期
	 * @param date
	 * @param days
	 * @return
	 */
	public static String getDateAddMonth(String date, int m) {
		DateFormat df = getDateFormat(yyyyMM_EN);
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(df.parse(date));
			cal.add(Calendar.MONTH, m);
			date = df.format(cal.getTime());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * 获取指定年月的第一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最小天数
		int lastDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		DateFormat df = getDateFormat(yyyy_MM_dd_EN);
		return df.format(cal.getTime());
	}
	
	/**
	 * 获取指定年月的第一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		DateFormat df = getDateFormat(yyyy_MM_dd_EN);
		return df.format(cal.getTime());
	}

	/**
	 * 获取昨天日期
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getYesterday(Date date) throws ParseException {
		DateFormat df = getDateFormat(yyyy_MM_dd_EN);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(df.parse(df.format(date)));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return df.format(calendar.getTime());
	}
	
//	public static List<Date> getThisWeekDates(){
//		List<Date> list = new ArrayList<Date>();
//        Calendar c = Calendar.getInstance();
//        // 今天是一周中的第几天
//        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK );
// 
//        if (c.getFirstDayOfWeek() == Calendar.SUNDAY) {
//            c.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        // 计算一周开始的日期
//        c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
//         
//        for (int i=1;i<=7;i++) {
//            c.add(Calendar.DAY_OF_MONTH, 1);
//            if(DateUtils.isSameDay(c.getTime(), new Date())) 
//            	break;
//            list.add(c.getTime());
//        }
//        return list;
//	}
	
	/**
	 * 10位时间戳转时间
	 * @param dateInt
	 * @param pattern
	 * @return
	 */
	public static String getIntToStr(String dateInt, String format){
		DateFormat df = getDateFormat(format);
		long times = Integer.parseInt(dateInt) * 1000L;
		Date date = new Date(times);
		return df.format(date);
	}
	
	/**
	 * 获取 10位时间戳
	 * @return
	 */
	public static Integer getDateInt(){
		return (int) (System.currentTimeMillis() / 1000);
	}
	
	/**
	 * 13位时间戳转时间
	 * @param time
	 * @param format
	 * @return
	 */
	public static String getLongToStr(long time, String format){
		Date date = new Date(time);
		return dateToDateString(date, format);
	}
	
	/**
	 * 获取两个小时间的间隔秒杀
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getIntervalSec(int start, int end){
		return (end - start) * 60 * 60;
	}
	
	/**
	 * 毫秒时间戳毫秒加小数点
	 * @param time
	 * @return
	 */
	public static String getMillsStr(long time){
		String timeStr = String.valueOf(time);
		String suffix = timeStr.substring(0, timeStr.length() - 3);
		String prefix = timeStr.substring(timeStr.length() - 3, timeStr.length());
		return suffix + "." + prefix;
	}
	
	/**
	 * 带小数点的毫秒时间戳转时间格式
	 * @param timeStr
	 * @param formatStr
	 * @return
	 */
	public static String longToString(String timeStr, String formatStr){
		long times = Long.parseLong(timeStr.replace(".", ""));
		Date date = new Date(times);
		return dateToDateString(date, formatStr);
	}
	
	/**
	 * 获取当天起始时间
	 * @return
	 */
	public static Long getTodayTime(){
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime();
	}
	
	public static Integer getTodayInt(){
		return (int) (getTodayTime() / 1000);
	}
	
	/**
	 * 获取当天结束时间
	 * @return
	 */
	public static Long getEndTime(){
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime().getTime();
	}
	
	public static Integer getTomorrowInt(){
		return (int) (getTomorrowTime() / 1000);
	}
	
	/**
	 * 获取第二天起始时间
	 * @return
	 */
	public static Long getTomorrowTime(){
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return  cal.getTime().getTime();
	}
	
	/**
	 * 获取当天指定小时的时间
	 * @param hour
	 * @return
	 */
	public static Long getPointHourTime(int hour){
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, hour);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime();
	}
	
	/**
	 * 获取当天n天后的h小时
	 * @param days
	 * @param hour
	 * @return
	 */
	public static Long getPointDateHourTime(int days, int hour){
		Calendar todayStart = Calendar.getInstance();
		todayStart.add(Calendar.DATE, days);
		todayStart.set(Calendar.HOUR_OF_DAY, hour);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime();
	}
	
	/**
	 * 时分秒转成秒数
	 * @param time
	 * @return
	 */
	public static Integer hourTosec(String time){
		if("null".equals(time) || StringUtils.isEmpty(time)){
			return null;
		}
		if(time.length() <= 5){
			time += ":00";
		}
		int index1 = time.indexOf(":");
		int index2 = time.indexOf(":",index1+1);
		int hh = Integer.parseInt(time.substring(0,index1));
		int mi = Integer.parseInt(time.substring(index1+1, index2));
		int ss = Integer.parseInt(time.substring(index2+1));
		return hh*60*60 + mi*60 + ss;
	}
	
	/**
	 * 时分秒转成秒数
	 * @param time
	 * @return
	 */
	public static Integer minTosec(String time){
		if(time.length() <= 5){
			time += ":00";
		}
		int index1 = time.indexOf(":");
		int index2 = time.indexOf(":",index1+1);
		int mi = Integer.parseInt(time.substring(0,index1));
		int ss = Integer.parseInt(time.substring(index1+1, index2));
		return mi*60 + ss;
	}
	
	
	public static boolean isDate(String dateTimeStr, String formatStr) {
		DateFormat df = getDateFormat(formatStr);
		try {
			df.parse(dateTimeStr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 判断时间是否在时间段内
	 * @param date			当前时间 yyyy-MM-dd HH:mm:ss
	 * @param strDateBegin 	开始时间 00:00:00
	 * @param strDateEnd 	结束时间 00:05:00
	 * @return
	 */
	public static boolean isInDate(String strDate, String strDateBegin, String strDateEnd) {
		// 截取当前时间时分秒
		int strDateH = Integer.parseInt(strDate.substring(11, 13));
		int strDateM = Integer.parseInt(strDate.substring(14, 16));
		int strDateS = Integer.parseInt(strDate.substring(17, 19));
		// 截取开始时间时分秒
		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
		// 截取结束时间时分秒
		int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
		int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
		if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
			// 当前时间小时数在开始时间和结束时间小时数之间
			if (strDateH > strDateBeginH && strDateH < strDateEndH) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM >= strDateBeginM && strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM == strDateBeginM && strDateS >= strDateBeginS && strDateS <= strDateEndS) {
				return true;
			}
			// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
			else if (strDateH >= strDateBeginH && strDateH == strDateEndH && strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH && strDateM == strDateEndM && strDateS <= strDateEndS) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 判断时间是否在时间段内
	 * @param date 当前时间 yyyy-MM-dd HH:mm:ss
	 * @param strDateBegin 开始时间 00:00:00
	 * @param strDateEnd 结束时间 00:05:00
	 * @return
	 */
	public static boolean isInDate(Date date, String strDateBegin,
			String strDateEnd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(date);
		// 截取当前时间时分秒
		int strDateH = Integer.parseInt(strDate.substring(11, 13));
		int strDateM = Integer.parseInt(strDate.substring(14, 16));
		int strDateS = Integer.parseInt(strDate.substring(17, 19));
		// 截取开始时间时分秒
		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
		// 截取结束时间时分秒
		int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
		int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
		if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
			// 当前时间小时数在开始时间和结束时间小时数之间
			if (strDateH > strDateBeginH && strDateH < strDateEndH) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
					&& strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM == strDateBeginM
					&& strDateS >= strDateBeginS && strDateS <= strDateEndS) {
				return true;
			}
			// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
			else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM == strDateEndM && strDateS <= strDateEndS) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static boolean isInTime(int time, int begin, int end){
		if(time >= begin && time < end){
			return true;
		}
		return false;
	}
	
	public static int getMinutest(String begin, String format){
		String nowMinutes = DateUtil.getCurDate("HH:mm");
		long time = DateUtil.compareDateStr("09:00", nowMinutes, "HH:mm");
		return (int) time;
	}
//------------------------

    /**
     *
     *
     * @param dateString �����ַ�
     * @param pattern    ģʽ
     * @return
     */
    public static boolean isUsePattern(String dateString, String pattern) {
        if (dateString == null) {
            throw new NullPointerException();
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            formatter.parse(dateString);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
    public static java.sql.Date parseSQLDate(String dateString, String pattern) {
        Date utilDate = parse(dateString, pattern);
        return (utilDate != null) ? (new java.sql.Date(utilDate.getTime())) : null;
    }

    public static Date nextDate(Date now) {
        if (now == null) {
            throw new NullPointerException();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static int getYearOfDate(Date date) {
        if (date == null) {
            return 1990;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonthOfDate(Date date) {
        if (date == null) {
            return 1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDayOfDate(Date date) {
        if (date == null) {
            return 1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * �õ���ǰsql.Date������
     *
     * @return
     */
    public static java.sql.Date currentSQLDate() {
        return new java.sql.Date(new Date().getTime());
    }

    /**
     * �õ���ǰ���ڵ�String ����
     *
     * @return
     */
    public static String currentStringTime() {
        return format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }
 // ####-##
	public static String getCurYearMonth() {
		String fullDate = String.valueOf(getCurYear());
		int temp = getCurMonth();
		if (temp < 10) {
			fullDate += "-0" + temp;
		} else {
			fullDate += "-" + temp;
		}
		return fullDate;
	}    
 // ##:##:##
	public static String getCurTime() {
		String time = getCurHourMinute();
		int temp = getCurSecond();
		if (temp < 10) {
			time += ":0" + temp;
		} else {
			time += ":" + temp;
		}
		return time;
	}

	// ##:##
	public static String getCurHourMinute() {
		String time;
		int temp = getCurHour();
		if (temp < 10) {
			time = "0" + temp + ":";
		} else {
			time = temp + ":";
		}
		temp = getCurMinute();
		if (temp < 10) {
			time += "0" + temp;
		} else {
			time += temp;
		}
		return time;
	}
	public static int getCurYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static int getCurMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static int getCurDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int getCurHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	public static int getCurMinute() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}

	public static int getCurSecond() {
		return Calendar.getInstance().get(Calendar.SECOND);
	}

	public static int getCurWeekDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}



	/**
	 * 得到一个SQL日期对象
	 * 
	 * @return Date
	 */
	public static java.sql.Date getSQLDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * 得到一个SQL日期对象
	 * 
	 * @return Date
	 */
	public static java.sql.Timestamp getSQLTimestamp() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	/**
	 * 自己编写的判断闰年的方法
	 * 
	 * @param year
	 *            int 年份
	 * @return boolean（true=闰年，false=非闰年）
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		}
		return false;
	}

	/**
	 * 自己编写的得到一年的天数的方法
	 * 
	 * @param year
	 *            int 年份
	 * @return int （非闰年=365，闰年=366）
	 */
	public static int getYearDays(int year) {
		if (isLeapYear(year)) {
			return 366;
		}
		return 365;
	}

	

	/**
	 * 比较两个日期的年份差距
	 * 
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @return int
	 */
	public static int compareDateOnYear(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		return y1 - y2;
	}


	/**
	 * 比较两个日期并返回两个日期相差多少天(d1－d2)
	 * 
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * @return int
	 */
	public static int compareDateOnDay(Date d1, Date d2) {
		if(d2==null)
			d2 = new Date();
		if (d1.getTime() == d2.getTime()) {
			return 0; // 日期相同返回0
		}
		int flag = -1;
		// 比较两个日期使日期较小的日期排在前面
		if (d1.getTime() > d2.getTime()) { // 日期一在日期二之后
			Date temp = d1;
			d1 = d2;
			d2 = temp;
			flag = 1;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int day1 = c1.get(Calendar.DAY_OF_YEAR);
		int day2 = c2.get(Calendar.DAY_OF_YEAR);
		int days = 0;
		for (int i = 1; i <= y2 - y1; i++) {
			days += getYearDays(y1);
		}
		return (days - day1 + day2) * flag;
	}


	public static long compareDateOnSystem(Date d1, Date d2) {
		return (long) (d1.getTime() - d2.getTime());
	}
    public static Date modifyByDate(Date date, int hour, int minute, int second)
    {
        Calendar gC = Calendar.getInstance();
        gC.setTime(date);
        gC.set(Calendar.HOUR_OF_DAY, hour);
        gC.set(Calendar.MINUTE, minute);
        gC.set(Calendar.SECOND, second);
        return gC.getTime();
    }

	/**
	 * 格式化一个日期对象，默认的格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            Date
	 * @param pattern
	 *            String
	 * @return String
	 */
	public static String format(Date date, String pattern) {
		String fmtStr = "";
		try{
		if (date != null) {
			java.text.DateFormat df = new java.text.SimpleDateFormat(pattern);
			fmtStr = df.format(date);
		}else{
			date = new Date();
			java.text.DateFormat df = new java.text.SimpleDateFormat(pattern);
			fmtStr = df.format(date);
		}
		}catch(Exception e){
			java.text.DateFormat df = new java.text.SimpleDateFormat(pattern);
			fmtStr = df.format(new Date());
		}
		return fmtStr;
	}

	/**
	 * 将一个字符串格式化为一个java.util.Date对象
	 * 
	 * @param obj
	 *            Object
	 * @return Date
	 */
	public static Date parse(Object obj) {
		try {
			if (obj == null) {
				return null;
			}
			String pattern="yyyy-MM-dd HH:mm:ss";
			String dateString = obj.toString().trim();
	
			if (dateString.length() == 0) {
				return null;
			}
			if (dateString.length() == 10) {
				//dateString += " 00:00:00";
				pattern="yyyy-MM-dd";
				
			}
			if (dateString.length() == 8||dateString.length() == 5) {
				if(dateString.length() == 5)
					dateString += ":00";
				pattern="HH:mm:ss";
			}
			java.text.DateFormat df = new java.text.SimpleDateFormat(pattern);
			return df.parse(dateString);
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}

	}
	/**
	 * 将一个字符串格式化为一个java.util.Date对象
	 * 
	 * @param obj
	 *            Object
	 * @return Date
	 */
	public static Date parseobj(Object obj,String pattern) {
		try {
			if (obj == null) {
				obj = new Date();
				//return null;
			}
			String dateString = obj.toString().trim();
			if (dateString.length() == 0) {
				return null;
			}
			java.text.DateFormat df = new java.text.SimpleDateFormat(pattern);
			return df.parse(dateString);
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}

	}
	/**
	 * 将一个字符串格式化为一个java.util.Date对象
	 * 
	 * @param obj
	 *            Object
	 * @return Date
	 */
	public static Date parseForHHMM(Object obj) {
		try {
			if (obj == null) {
				return null;
			}
			String dateString = obj.toString().trim();
			if (dateString.length() == 0) {
				return null;
			}
			if (dateString.length() == 10) {
				dateString += " 00:00:00";
			}
			java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
			return df.parse(dateString);
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 根据指定的pattern格式化类型为String的日期 update by zhuzf
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static String format(String dateStr, String pattern) {
		if(dateStr==null||dateStr.equals(""))
			return format(new Date(), pattern);
		java.text.SimpleDateFormat df = null;
		if (dateStr != null && dateStr.length() >= 19) {
			df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
		} else if (dateStr != null && dateStr.length() == 10) {
			df = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
		}else if(dateStr.length()>10&&dateStr.length()<19){
			df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
			dateStr= dateStr+":00";
		}

		java.util.Date date = null;
		try {
			/*
			 * if(dateStr!=null&&dateStr.length()>10){
			 * dateStr=dateStr.substring(0,10); }
			 */
			date = df.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format(date, pattern);
	}

	

	public final static java.util.List separateDateStr(String startDate, String endDate, long step) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.List rtn = separateDate(startDate, endDate, step);
		int size = rtn.size();
		for (int i = 0; i < size; i++) {
			rtn.set(i, df.format(rtn.get(i)));
		}
		return rtn;
	}

	public final static java.util.List separateDate(String startDate, String endDate, long step) {
		Date d1 = parse(startDate);
		Date d2 = parse(endDate);
		long start = d1.getTime();
		long end = d2.getTime();
		if (start > end) {
			throw new IllegalArgumentException("开始日期（" + startDate + "）晚于结束日期（" + endDate + "）");
		}
		java.util.List rtn = new java.util.ArrayList();
		long tmp = start;
		while (end > tmp) {
			rtn.add(new Date(tmp));
			tmp += step;
		}
		rtn.add(new Date(tmp));
		return rtn;
	}

	  /**
     * 
			前天时间
     * @return
     */
    public static Date getYesterday()
    {
        return addDays(-1);
    }
    
    /**
     * 当前时间的 days天前 正数 或者days天后 负数

     * @param days int
     * @return Date
     */
    public static Date addDays(int days)
    {
        return addDays(new Date(), days);
    }

    /**
     * 指定那一天的 days天前或者days天后?
     * @param srcDate Date
     * @param days int
     * @return Date
     */
    public static Date addDays(Date srcDate, int days)
    {
        GregorianCalendar gCanlendar = new GregorianCalendar();
        gCanlendar.setTime(srcDate);
        gCanlendar.add(gCanlendar.DAY_OF_MONTH,days);
        return gCanlendar.getTime();
    }
    /**
     * 指定那一天的 增加多少分钟?
     * @param srcDate Date
     * @param days int
     * @return Date
     */
    public static Date addSeconds(Date srcDate, int second)
    {
        GregorianCalendar gCanlendar = new GregorianCalendar();
        gCanlendar.setTime(srcDate);
        gCanlendar.add(gCanlendar.HOUR_OF_DAY,second);
        return gCanlendar.getTime();
    }

    /**
     * 指定日期的 months月前或者months月后
     * @param srcDate
     * @param months
     * @return
     */
    public static Date addMonths(Date srcDate, int months)
    {
        GregorianCalendar gCanlendar = new GregorianCalendar();
        gCanlendar.setTime(srcDate);
        gCanlendar.add(gCanlendar.MONTH, months);
        return gCanlendar.getTime();        
    }
    
    /**
     * ?指定日期 的year年前或者year年后
     * @param srcDate Date
     * @param year int
     * @return Date
     */
    public static Date addYears(Date srcDate, int year)
    {
        GregorianCalendar gCanlendar = new GregorianCalendar();
        gCanlendar.setTime(srcDate);
        gCanlendar.add(gCanlendar.YEAR,year);
        return gCanlendar.getTime();
    }
    /**
     * yi字符串转化一定的格式然后以日期形式输出
     *  @param date String
     * @param srcPattern String
     * @return Date
     */
    public static Date parse(String date, String pattern)
    {
        try
        {
        	if(date==null)
        		return new Date();
        	if(date.length()>10&&date.length()<19)
        		date = date+":00";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(date);
        }
        catch(java.text.ParseException e)
        {
            return null;
        }
    }
    
    /**
     *与当前日期的比较 大于当前日期为-1，小于当前日期为1，等于当前日期为0
     * @param dest Date
     * @return int 0?1dest,1?dest
     */
    public static int compareTo(Date dest)
    {
        return compareTo(new Date(), dest);
    }

    /**
     * srcdest二个日期的比较 前者大于后者为1，小于后者为-1，等于后者为0
     * @param src Date
     * @param dest Date
     * @return int 0?1?srcdest,1?src?dest
     */
    public static long compareTimeTo(Date src, Date dest)
    {
        long src1 = parse(format(src, "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss").getTime();
        long src2 = parse(format(dest,"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss").getTime();
        long i = src1-src2;
        return i;
    }	
    public static int compareTo(Date src, Date dest)
    {
        Date src1 = parse(format(src, "yyyy-MM-dd"),"yyyy-MM-dd");
        Date src2 = parse(format(dest,"yyyy-MM-dd"),"yyyy-MM-dd");
        int i = src1.compareTo(src2);
        return i;
    }    
	//20121114084153 转换为日期 yyyy-MM-dd HH:mm:ss
	public static Date dateNumtoDate(String dateNum){
		String rdate =format(new Date());
		try{
			if(!(dateNum==null||dateNum.equalsIgnoreCase(""))){
				if(dateNum.length()>11){
					rdate = dateNum.substring(0, 4)+"-";
					rdate += dateNum.substring(4, 6)+"-";
					rdate += dateNum.substring(6, 8)+" ";
					rdate += dateNum.substring(8, 10)+":";
					rdate += dateNum.substring(10, 12)+":00";
				}
			}	
		}catch(Exception e){
			return parse(format(new Date()));
		}
		return parse(rdate);
	}
	
	//20121114084153 转换为日期格式 字符串
	public static String dateNumtoDateStr(String dateNum){
		String rdate =format(new Date());
		try{		
			if(!(dateNum==null||dateNum.equalsIgnoreCase(""))){
				if(dateNum.length()>15){
					return dateNum;
				}if(dateNum.length()>13||dateNum.length()<15){
					rdate = dateNum.substring(0, 4)+"-";
					rdate += dateNum.substring(4, 6)+"-";
					rdate += dateNum.substring(6, 8)+" ";
					rdate += dateNum.substring(8, 10)+":";
					rdate += dateNum.substring(10, 12)+":";
					rdate += dateNum.substring(12, 14);
				}else if(dateNum.length()<=13){
					rdate = dateNum.substring(0, 4)+"-";
					rdate += dateNum.substring(4, 6)+"-";
					rdate += dateNum.substring(6, 8)+" ";
					rdate += dateNum.substring(8, 10)+":";
					rdate += dateNum.substring(10, 12)+":00";
				
				}
			}	
		}catch(Exception e){
			return dateNum;
		}
		return rdate;
	}	

	public static boolean doTime(String beginTime,String endTime){//beginTime如果21:00:00,endTime为8:30:00
		try { 
			boolean b=false;
			SimpleDateFormat d= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
			SimpleDateFormat d1= new SimpleDateFormat("yyyy-MM-dd");//格式化时间
			String sNowTime=d.format(new Date());//按以上格式 将当前时间转换成字符串		
			String date = d1.format(new Date());
			long lnow=0;//现在时间数		
			long lstart=0;//开始时间数
			long lend=0;//结束时间数			
			lnow = parse(sNowTime,"yyyy-MM-dd HH:mm:ss").getTime();//现在时间
			lstart = parse((date+" "+beginTime),"yyyy-MM-dd HH:mm:ss").getTime();
			lend = parse((date+" "+endTime),"yyyy-MM-dd HH:mm:ss").getTime();
	    	if(lnow>lstart && lnow<lend){
	    		b=true;
	    	}
	    	return b;
		} catch (IllegalArgumentException e) { 
			e.printStackTrace(); 
		}
		return false;
	}	
	/**
	 * 日期加减
	 * 
	 * @param format
	 *            y:年运算 m:月运算 d：日运算
	 * @param number
	 *            加减天数
	 * @param strYYYYMMDD
	 *            被运算日期
	 * @return 运算后结果日期
	 */
	public static String addDate(String format, int number, String strYYYYMMDD) {
		Calendar cal = null;
		String strDate = "";
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			cal = Calendar.getInstance();
			cal.clear();
			date = sdf.parse(strYYYYMMDD);
			cal.setTime(date);

			if (format.toLowerCase().equals("y") == true) {
				cal.add(Calendar.YEAR, number);
			} else {
				if (format.toLowerCase().equals("m") == true) {
					cal.add(Calendar.MONTH, number);

				} else {
					cal.add(Calendar.DATE, number);
				}
			}
			date = cal.getTime();
			strDate = sdf.format(date);
		} catch (Exception e) {
			strDate = "";
		}
		return strDate;
	}

	/**
	 * 在日期上加一定的毫秒得到一个新日期
	 * 
	 * @param date
	 *            被运算的日期
	 * @param milliseconds
	 *            加减的毫秒数
	 * @return
	 */
	public static Date addMillisecond(Date date, long milliseconds) {
		long baseMilliseconds = date.getTime();
		Date newDate = new Date();
		newDate.setTime(baseMilliseconds + milliseconds);
		return newDate;
	}

	/**
	 * 得到某年某月的天数
	 * 
	 * @param year
	 *            某年
	 * @param month
	 *            某月
	 * @return
	 */
	public static int showDaysOfMonth(int year, int month) {
		int days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (2 == month && 0 == (year % 4) && (0 != (year % 100) || 0 == (year % 400))) {
			days[1] = 29;
		}
		return (days[month - 1]);
	}
	
	  /**
     * 取小时的下拉菜单，如果有初值，选定初值
     * @param name
     * @param selected
     * @return
     */
    public static String getOptionsByHour(String name, String selected) {
        StringBuffer returnValue = new StringBuffer();
        returnValue.append("<select name=\"" + name + "\">");
        for (int i = 0; i < 24; i++) {
            String hour = (i < 10 ? "0" + i : String.valueOf(i));
            returnValue.append(
                    "<option value=\"" + hour + "\"" +
                    (hour.equals(selected) ? "selected" : "") + ">");
            returnValue.append(hour);
            returnValue.append("</option>");
        }
        returnValue.append("</select>");
        return returnValue.toString();
    }

    /**
     * 取分钟的下拉菜单，如果有初值，选定初值
     * @param name
     * @param selected
     * @return
     */
    public static String getOptionsByMinute(String name, String selected) {
        try {
            StringBuffer returnValue = new StringBuffer();
            returnValue.append("<select name=\"" + name + "\">");
            for (int i = 0; i < 60; i++) {
                String minute = (i < 10 ? "0" + i : String.valueOf(i));
                returnValue.append(
                        "<option value=\"" + minute + "\"" +
                        (minute.equals(selected) ? " selected" : "") + ">");
                returnValue.append(minute);
                returnValue.append("</option>");
            }
            returnValue.append("</select>");
            return returnValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * 
     * @return Date
     */
    public static Date getNow()
    {
        return new Date();
    }
   

    /**
     * 当月第一天
     * @return
     */
    public static String getFirstDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        //Date theDate = calendar.getTime();
        
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
        return str.toString();

    }
    
    /**
     * 当月最后一天
     * @return
     */
    public static String getLastDay(Date date) {
    	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last);
        day_last = endStr.toString();
        return day_last;
    }
    public static String getWeek(Date date){
    	if(date==null)
    		return "星期日";
		String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		} 
		return weeks[week_index];
	}
    //获得一周开始日期和结束日期
    public static String[] getTime(Date cdate){  
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");   
        SimpleDateFormat Fm = new SimpleDateFormat("E");   
        String time = dateFm.format(cdate);  //当前时间  
        String d = Fm.format(cdate); //星期几  
        String xingYi = "";  //星期一的时间  
        String xingTi = "";  //星期天的时间  
        String[] timeArr = time.split("-");  
        int yyy = Integer.parseInt(timeArr[0]);  //取得当前时间的年  
        int MMM = Integer.parseInt(timeArr[1]);  //取得当前时间的月  
        int ddd = Integer.parseInt(timeArr[2]);  //取得当前时间的天  

        int[] timeArray = new int[6];  
        System.out.println(d);
        if ("星期一".equals(d) || "星期一" == d) {  
            //返回年份、月份跟星期一或星期天的日期  
            timeArray = tianShu(ddd, MMM, 1, yyy, 0, 6);  
        }else if("星期二".equals(d) || "星期二" == d){  
            timeArray = tianShu(ddd, MMM, 2, yyy, 1, 5);  
        }else if("星期三".equals(d) || "星期三" == d){  
            timeArray = tianShu(ddd, MMM, 3, yyy, 2, 4);  
        }else if("星期四".equals(d) || "星期四" == d){  
            timeArray = tianShu(ddd, MMM, 4, yyy, 3, 3);  
        }else if("星期五".equals(d) || "星期五" == d){  
            timeArray = tianShu(ddd, MMM, 5, yyy, 4, 2);  
        }else if("星期六".equals(d) || "星期六" == d){  
            timeArray = tianShu(ddd, MMM, 6, yyy, 5, 1);  
        }else if("星期天".equals(d) || "星期天" == d){  
            timeArray = tianShu(ddd, MMM, 7, yyy, 6, 0);  
        }else{  
            System.out.println("黑色星期八");  
            return null;  
        }  
        return new String[]{timeArray[0]+"-"+timeArray[1]+"-"+timeArray[4] , timeArray[2]+"-"+timeArray[3]+"-"+timeArray[5]};  
    }  
  
  
    /* 
     * 判断当前月份是大月还是小月 
     * 返回月份共有多少天 
     */  
    public static  int panDuanDaXiao(int MM,int yyy){  
        //如果是二月份  
        if(MM == 2){  
            //如果是闰年  
            if( yyy % 4 == 0 && yyy % 100 != 0 || yyy % 400 == 0 )  
            {  
                return 29;  
            }  
            else  
            {  
                return 28;  
            }   
        }  
        //如果是小于七月份的单月  
        if(MM <= 7 && MM % 2 != 0){  
            return 31;  
        }  
        //如果是小于七月份的双月、二月份除外  
        if(MM < 7 && MM % 2 == 0){  
            return 30 ;  
        }  
  
        //如果是大于等于8月份的单月  
        if(MM >= 8 && MM % 2 != 0){  
            return 30;  
        }  
        //如果是大于等于8月份的双月  
        if(MM >= 8 && MM % 2 == 0){  
            return 31;  
        }  
        return 0;  
    }  
  
    /* 
     * 计算星期天跟星期一的天数 
     * 返回年份、月份(星期一/星期天)跟星期一或星期天的日期 
     * 参数dd为当前天数,MM为当前月数、ee为当前星期数,yyy为当前年数,can1为距离星期一的天数,can1为距离星期天的天数 
     * 其中can1跟can2最为重要 
     */  
    public static  int[] tianShu(int dd, int MM ,int ee,int yyy,int can1,int can2){  
  
        int xiYi = 0 ;  //星期一的日期天数  
        int xiTi = 0;  //星期天的日期天数  
        int mmNum = panDuanDaXiao(MM, yyy);  //取得月份天数  
        int mmNum1 = panDuanDaXiao(MM - 1, yyy);  //取得上一个月的月份天数  
  
        int yiMM = MM ;  //星期一的月份  
        int tiMM = MM;  //星期天的月份  
          
        int yiYYY = yyy;  //星期一的年份  
        int tiYYY = yyy;  //星期天的年份  
          
          
        //如果当前天数减去星期数大于等于0、不用返回上一个月计算  
        if( dd - ee >= 0 ){  
            if(1 == ee){  
                xiYi = dd ;  
            }else{  
                xiYi = dd - can1 ;  //星期一的日期天数  
            }  
        }else{  
            //如果当前月份不等于1月  
            if(yiMM > 1 ){  
                yiMM = yiMM - 1 ; //当前月份减一  
            }else{  
                yiMM = 12 ;  
                yiYYY = yiYYY - 1 ;  
            }  
            xiYi = mmNum1 + dd - ee ; //星期一的日期天数  
        }  
          
          
        //如果星期天的日期天数小于等于月份天数  
        if(7 == ee){  
            xiTi = dd ;  
        }else{  
            xiTi = dd  + can2 ;  
        }  
        //如果星期天的日期小于等于当前月份日期  
        if(xiTi <= mmNum){  
            return new int [] {yiYYY,yiMM,tiYYY,tiMM,xiYi,xiTi};  
        }else{  
            xiTi = xiTi - mmNum ; //星期天的日期天数  
            //如果当前月份不等于12月  
            if(tiMM < 12 ){  
                tiMM = tiMM + 1 ; //当前月份加一  
            }else{  
                tiMM = 1 ;  
                tiYYY = tiYYY + 1 ;  
            }  
            return new int[]{yiYYY,yiMM,tiYYY,tiMM, xiYi , xiTi};  
        }  
    }  
    /**
     * 
     * @param cdate
     * @param d
     * @param n 0,1
     * @return
     */
    public static Date MatchDate(Date cdate,int d,int n){
    	String sdate = DateUtil.format(cdate, "yyyy-MM-dd");
    	if(n%2==0){
    		return DateUtil.addDays(DateUtil.parse(sdate+" 09:00:00", "yyyy-MM-dd HH:mm:ss"), d-1);
    	}else
    		return DateUtil.addDays(DateUtil.parse(sdate+" 14:00:00", "yyyy-MM-dd HH:mm:ss"), d-1);
    }
    public static String getDatePoor(Date endDate, Date nowDate) {
    	 if(endDate==null||nowDate==null)
    		 return null;
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
    public static String getNeedDate(String PLANDATE,//日期 年月日 比赛起始日期 不能为空
			Integer NUM,//隔几天比赛 比如 0天 就是每天都比赛，1天就是隔一天比赛 赶紧具体时间		
			String PLANTIME,//具体比赛时间  时分秒 多个用;隔开 不能为空
			Integer idate){
    	String[] aplantime = PLANTIME.split("\\;");
    	int todaynum = aplantime.length;//一天有几场次比赛
    	int thatdaynum = ((idate-1)/todaynum)+NUM;//获得应该在哪里一天
    	if(idate<todaynum)
    		thatdaynum = ((idate-1)/todaynum);
    	int timenum = (idate-1)%todaynum;//应该在当天的那个时间数里
    	String currtime = aplantime[timenum];
    	if(currtime.length()<8)
    		currtime = currtime+":00";
    	Date currdate = addDays(parse(PLANDATE,"yyyy-MM-dd"),thatdaynum);
    	String currdatetime = format(currdate,"yyyy-MM-dd")+" "+currtime;
    	return currdatetime;
    }
	/** 
     * 获取最近12个月，经常用于统计图表的X轴 
     */  
    public static String[] getLast12Months(){  
          
        String[] last12Months = new String[12];  
          
        Calendar cal = Calendar.getInstance();  
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1); //要先+1,才能把本月的算进去</span>  
        for(int i=0; i<12; i++){  
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1); //逐次往前推1个月  
            last12Months[11-i] = cal.get(Calendar.YEAR)+ "-" +(cal.get(Calendar.MONTH)+1); 
        }          
        return last12Months;  
    } 
    //时间返回标签 
    public static String replytimelable(Date ucreatedate){
    	long mtime = compareTimeTo(new Date(),ucreatedate)/1000/60;//分钟
    	if(mtime==0)
    		return "刚刚";
    	else if(mtime>0&&mtime<60)
    		return mtime+"分钟前";
    	else if(mtime>=60&&mtime<24*60)
    		return mtime/60+"小时前";
    	else if(mtime>=24*60&&mtime<24*60*2)
    		return "昨天";
    	else if(mtime>=24*60*2&&mtime<24*60*3)
    		return "前天";
    	else
    		return DateUtil.format(ucreatedate, "yyyy-MM-dd");
    }
	/** 
	*  N天前到今天的每天日期
	*/  
	public static String getLastTimeInterval(int day ) {  
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");  //字符串转换 "yyyy-MM-dd"
		  Calendar c = Calendar.getInstance();  
		//new Date().getTime();这个是获得当前电脑的时间，你也可以换成一个随意的时间
		  c.setTimeInMillis(new Date().getTime());
		  c.add(Calendar.DATE, day);//天后的日期
		  Date date= new Date(c.getTimeInMillis()); //将c转换成Date
		  return formatDate.format(date);
	}  
	/** 
     * 时间戳转换成日期格式字符串 
     * @param seconds 精确到秒的字符串 
     * @param formatStr 
     * @return 
     */  
    public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }   
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if(seconds.length()>10)
        	return sdf.format(new Date(Long.valueOf(seconds)));
        else
        	return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }  
    /** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
      
    /** 
     * 取得当前时间戳（精确到秒） 
     * @return 
     */  
    public static String timeStamp(){  
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);  
        return t;  
    }  
	public static void main(String[] args) {
		System.out.println(timeStamp());
	System.out.println(DateUtil.timeStamp2Date("1546425423", "yyyy-MM-dd"));	
	}
}
