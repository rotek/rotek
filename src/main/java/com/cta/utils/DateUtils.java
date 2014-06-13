/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.cta.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: DateUtils
 * @Description: 日期的工具类
 * @author chenwenpeng
 * @date 2013-7-13 下午3:43:21
 */
public class DateUtils {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat();

	private static Calendar calendar = Calendar.getInstance();

	/**
	 * @Title: formatTime
	 * @Description: 格式化时间
	 * @param @param date
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String formatDateTime(Date date, String reg) {
		if (null == date || null == reg) {
			return null;
		}
		return new SimpleDateFormat(reg).format(date);
	}

	/**
	 * @Title: formatTime
	 * @Description: 格式化时间
	 * @param @param date
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String formatTime(Date date) {
		if (null == date) {
			return null;
		}
		return formatDateTime(date, "HH:mm:ss");
	}

	/**
	 * @Title: parseDateTime
	 * @Description: 解析时间
	 * @param @param time_str
	 * @param @return
	 * @param @throws ParseException
	 * @return Date
	 * @throws
	 */
	public static Date parseTime(String time_str) throws ParseException {
		if (null == time_str) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.parse(time_str);
	}

	/**
	 * @Title: compareDateTime
	 * @Description: 比较时间
	 * @param @param date
	 * @param @param begin
	 * @param @param end
	 * @return void
	 * @throws
	 */
	public static boolean compareDateTime(Date date, Date begin, Date end) {
		if (null == date || null == begin || null == end) {
			return false;
		}
		return date.getTime() >= begin.getTime()
				&& date.getTime() <= end.getTime();
	}

	/**
	 * @Title: compareDateTime
	 * @Description: 比较时间,大于返回true，小于等于返回false
	 * @param @param date
	 * @param @param begin
	 * @param @param end
	 * @return void
	 * @throws
	 */
	public static boolean compareDateTime(Date date, Date compareTo) {
		if (null == date || null == compareTo) {
			return false;
		}
		return date.getTime() > compareTo.getTime();
	}

	/**
	 * @Title: parseDateTimeByTime
	 * @Description: 根据string类型的时间，解析出当前完整的日期时间 2013-08-15 10:26:00
	 * @param str_time
	 * @return
	 * @throws ParseException
	 * @return Date
	 * @throws
	 */
	public static Date parseDateTimeByTime(String str_time)
			throws ParseException {
		if (StringUtils.isBlank(str_time)) {
			return null;
		}
		Date now = new Date();
		Calendar calendar_datetime = Calendar.getInstance();
		calendar_datetime.setTime(now);

		Date time = DateUtils.parseTime(str_time);
		Calendar calendar_time = Calendar.getInstance();
		calendar_time.setTime(time);

		calendar_datetime.set(calendar_datetime.get(Calendar.YEAR),
				calendar_datetime.get(Calendar.MONTH),
				calendar_datetime.get(Calendar.DAY_OF_MONTH),
				calendar_time.get(Calendar.HOUR_OF_DAY),
				calendar_time.get(Calendar.MINUTE),
				calendar_time.get(Calendar.SECOND));
		return calendar_datetime.getTime();
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(DateUtils.formatTime(new Date()));
	}

	/**
	 * @param now
	 * @param dayInterval
	 * @return
	 */
	public static Date getDateByDayInterval(Date now, int dayInterval) {

		Calendar calendar_datetime = Calendar.getInstance();
		calendar_datetime.setTime(now);

		calendar_datetime.set(Calendar.DAY_OF_MONTH, dayInterval);

		return calendar_datetime.getTime();
	}

	/**
	 * @param date
	 * @return
	 */
	public static Date getStartTime(Date date) {

		synchronized (calendar) {
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}
		return calendar.getTime();
	}

	/**
	 * @param day
	 * @return
	 */
	public static Date getEndTime(Date day) {
		synchronized (calendar) {
			calendar.setTime(day);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			return calendar.getTime();
		}
	}
}
