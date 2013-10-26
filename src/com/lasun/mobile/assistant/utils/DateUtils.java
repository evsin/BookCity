package com.lasun.mobile.assistant.utils;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressLint("SimpleDateFormat")
public class DateUtils {
	public static final String YYYYMMDD = "yyyy-MM-dd";
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDHHMMSS2 = "yyyyMMddhhmmss";
	public static final String YYYYMMDDHHMMSS3 = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDHHMMSS4 = "yyyyMMdd";
	public static final String HHMMSS = "HH:mm:ss";
	public static Date parseStringToDate(String dateStr, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 */
	public static String getCurrentStampTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDDHHMMSS);
		return dateFormat.format(new Date());
	}
	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 */
	public static String getCurrentStampTime2() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDDHHMMSS2);
		return dateFormat.format(new Date());
	}
	public static String getCurrentStampTime3() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDDHHMMSS3);
		return dateFormat.format(new Date());
	}
	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(new Date());
	}
	/**
	 * 获取当前年
	 * 
	 * @return
	 */
	public static String getCurrentYear() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		return dateFormat.format(new Date());
	}
	/**
	 * 获取当前月
	 * 
	 * @return
	 */
	public static String getCurrentMonth() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		return dateFormat.format(new Date());
	}
	/**
	 * 获取当前日
	 * 
	 * @return
	 */
	public static String getCurrentDay() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		return dateFormat.format(new Date());
	}
	/**
	 * 获取当前时钟
	 * 
	 * @return
	 */
	public static String getCurrentHour() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh");
		return dateFormat.format(new Date());
	}
	/**
	 * 获取当前分钟
	 * 
	 * @return
	 */
	public static String getCurrentMinute() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm");
		return dateFormat.format(new Date());
	}
	public static String judgeAMPM() {
		GregorianCalendar ca = new GregorianCalendar();
		return ca.get(GregorianCalendar.AM_PM) + "";
	}
	public static String lastWeek() {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date)) - 6;
		if (day < 1) {
			month -= 1;
			if (month == 0) {
				year -= 1;
				month = 12;
			}
			if (month == 4 || month == 6 || month == 9 || month == 11) {
				day = 30 + day;
			} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				day = 31 + day;
			} else if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
					day = 29 + day;
				else
					day = 28 + day;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10)
			m = "0" + month;
		else
			m = month + "";
		if (day < 10)
			d = "0" + day;
		else
			d = day + "";
		return y + m + d;
	}
	public static String lastMonth() {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date)) - 1;
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
		if (month == 0) {
			year -= 1;
			month = 12;
		} else if (day > 28) {
			if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
					day = 29;
				} else
					day = 28;
			} else if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
				day = 30;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10)
			m = "0" + month;
		else
			m = month + "";
		if (day < 10)
			d = "0" + day;
		else
			d = day + "";
		return y + m + d;
	}
	public static long dateToLong(String parse) {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS4);
		Date date;
		try {
			date = sdf.parse(parse);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
}