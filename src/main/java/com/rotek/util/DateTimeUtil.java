package com.rotek.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

public class DateTimeUtil {

	private static Calendar calendar = Calendar.getInstance();
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat dateFormatForHour = new SimpleDateFormat(
			"yyyyMMddHH");
	private static byte[] lock = new byte[0];

	/**
	 * 格式化时间为yyyyMMddHH
	 * 
	 * @param dateHour
	 * @return
	 */
	public static Long formatDateHour(Date dateHour) {
		synchronized (dateFormatForHour) {
			String dh = dateFormatForHour.format(dateHour);
			return Long.valueOf(dh);
		}
	}

	/**
	 * 获取距离目前第n天的时间
	 * 
	 * @param n
	 * @return
	 */
	public static String getNDay(int n) {
		synchronized (calendar) {
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, n);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(calendar.getTime());
		}
	}

	/**
	 * 获取距离目前第n天的时间
	 * 
	 * @param n
	 * @return
	 */
	public static String getNDay(Date day, int n) {
		synchronized (calendar) {
			calendar.setTime(day);
			calendar.add(Calendar.DATE, n);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(calendar.getTime());
		}
	}

	/**
	 * 获取距离目前第N月的时间
	 * 
	 * @param day
	 * @param n
	 * @return
	 */
	public static String getNMonth(Date day, int n) {
		synchronized (calendar) {
			calendar.setTime(day);
			calendar.add(Calendar.MONTH, n);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			return dateFormat.format(calendar.getTime());
		}
	}

	/**
	 * format为yyyy-MM-dd
	 * 
	 * @param n
	 * @return
	 */
	public static String format(Date day) {
		synchronized (dateFormat) {
			return dateFormat.format(day);
		}
	}

	/**
	 * format为yyyy-MM-dd
	 * 
	 * @param n
	 * @return
	 */
	public static String format(Date date, String pattern) {
		synchronized (lock) {
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			if (date == null)
				return "";
			return dateFormat.format(date);
		}
	}

	/**
	 * 获取距离目前第n天的时间
	 * 
	 * @param n
	 * @return
	 */
	public static Date getNDate(Date day, int n) {
		synchronized (calendar) {
			calendar.setTime(day);
			calendar.add(Calendar.DATE, n);
			return calendar.getTime();
		}
	}

	/**
	 * 返回两个日期之间的天的列表
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> getDayList(Date start, Date end) {
		if (start == null || end == null || start.after(end)) {
			return new ArrayList<String>(0);
		}
		List<String> dayList = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		synchronized (calendar) {
			calendar.setTime(start);
			do {
				start = calendar.getTime();
				dayList.add(dateFormat.format(start));
				calendar.add(Calendar.DATE, 1);
			} while (start.before(end));
		}
		return dayList;
	}

	/**
	 * 返回两个日期之间的天的列表
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Date> getDateList(Date start, Date end) {
		if (start == null || end == null || start.after(end)) {
			return new ArrayList<Date>(0);
		}
		List<Date> dayList = new ArrayList<Date>();
		synchronized (calendar) {
			calendar.setTime(start);
			do {
				start = calendar.getTime();
				dayList.add(start);
				calendar.add(Calendar.DATE, 1);
			} while (start.before(end));
		}
		return dayList;
	}

	public static String getDurationString(Long ms) {
		String duration = "";
		Long temp = ms / (86400 * 365 * 1000L);
		if (temp > 0) {
			duration += temp + "年";
		}
		ms = ms % (86400 * 365 * 1000L);

		temp = ms / (86400 * 30 * 1000L);
		if (temp > 0) {
			duration += temp + "月";
		}
		ms = ms % (86400 * 30 * 1000L);

		temp = ms / (86400 * 1000L);
		if (temp > 0) {
			duration += temp + "天";
		}
		ms = ms % (86400 * 1000L);

		temp = ms / (3600 * 1000L);
		if (temp > 0) {
			duration += temp + "小时";
		}
		ms = ms % (3600 * 1000L);

		temp = ms / (60 * 1000L);
		if (temp > 0) {
			duration += temp + "分";
		}
		ms = ms % (60 * 1000L);
		temp = ms / 1000L;

		duration += temp + "秒";

		return duration;

	}

	public static int getDuration(Date start, Date end) {
		Long ms = end.getTime() - start.getTime();
		Long d = ms / 86400000L;

		return d.intValue();

	}

	/**
	 * 获取间隔的小时数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDurationHours(Date start, Date end) {
		Long ms = end.getTime() - start.getTime();
		Long d = ms / 3600000L;

		return d.intValue();

	}

	/**
	 * 启动的时间到现在时间之间的间隔
	 * 
	 * @param ms
	 * @return
	 */
	public static String msToDuration(Long ms) {
		Long now = System.currentTimeMillis();
		ms = now - ms;
		return getDurationString(ms);
	}

	public static List<String> getHourOfDay(Date day) {
		synchronized (calendar) {
			List<String> list = new ArrayList<String>(24);
			calendar.setTime(day);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			for (int i = 0; i < 24; i++) {
				calendar.set(Calendar.HOUR_OF_DAY, i);
				list.add(dateFormatForHour.format(calendar.getTime()));
			}
			return list;
		}
	}

	public static List<Long> getHourOfDayAsLong(Date day) {
		synchronized (calendar) {
			List<Long> list = new ArrayList<Long>(24);
			calendar.setTime(day);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			for (int i = 0; i < 24; i++) {
				calendar.set(Calendar.HOUR_OF_DAY, i);
				list.add(Long.valueOf(dateFormatForHour.format(calendar
						.getTime())));
			}
			return list;
		}
	}

	public static List<Date> getDateHourOfDay(Date day) {
		synchronized (calendar) {
			List<Date> list = new ArrayList<Date>(24);
			calendar.setTime(day);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			for (int i = 0; i < 24; i++) {
				calendar.set(Calendar.HOUR_OF_DAY, i);
				list.add(calendar.getTime());
			}
			return list;
		}
	}

	public static Date getBeginTime(Date day) {
		synchronized (calendar) {
			calendar.setTime(day);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
	}

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

	public static Date getBeginTime(Date day, int n) {
		synchronized (calendar) {
			calendar.setTime(day);
			calendar.add(Calendar.DATE, n);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
	}

	public static Date getEndTime(Date day, int n) {
		synchronized (calendar) {
			calendar.setTime(day);
			calendar.add(Calendar.DATE, n);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			return calendar.getTime();
		}
	}

	/**
	 * 获取当前日期所在周的 起始日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeginDateOfWeek(Date date) {

		synchronized (calendar) {

			calendar.setTime(date);

			int week = calendar.get(Calendar.DAY_OF_WEEK) + 6;
			week = week > 7 ? week - 8 : week - 1;

			calendar.add(Calendar.DAY_OF_MONTH, -week);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
	}

	/**
	 * 根据日期获取周几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekDay(Date date) {
		String weekDay = null;

		synchronized (calendar) {
			calendar.setTime(date);
			int week = calendar.get(Calendar.DAY_OF_WEEK);

			if (week == Calendar.SUNDAY) {
				weekDay = "周日";
			} else if (week == Calendar.MONDAY) {
				weekDay = "周一";
			} else if (week == Calendar.TUESDAY) {
				weekDay = "周二";
			} else if (week == Calendar.WEDNESDAY) {
				weekDay = "周三";
			} else if (week == Calendar.THURSDAY) {
				weekDay = "周四";
			} else if (week == Calendar.FRIDAY) {
				weekDay = "周五";
			} else {
				weekDay = "周六";
			}

			return weekDay;
		}
	}

	/**
	 * 获取当前日期所在周的 上一个周的起始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeginDateOfPrevWeek(Date date) {

		synchronized (calendar) {

			Date endDateOfPrevWeek = DateTimeUtil.getEndDateOfPrevWeek(date);
			calendar.setTime(endDateOfPrevWeek);
			calendar.add(Calendar.DAY_OF_MONTH, -6);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			return calendar.getTime();
		}
	}

	/**
	 * 获取当天所在周的上一周 的结束时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndDateOfPrevWeek(Date date) {

		synchronized (calendar) {

			Date beginDateOfWeek = DateTimeUtil.getBeginDateOfWeek(date);
			calendar.setTime(beginDateOfWeek);
			calendar.add(Calendar.MILLISECOND, -1);

			return calendar.getTime();
		}
	}

	/**
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date, String reg) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}

		try {
			DateFormat dateFormat = new SimpleDateFormat(reg);
			return dateFormat.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取date所在时间的 小时数
	 * 
	 * @param now
	 * @return
	 */
	public static int getHourByDate(Date date) {

		if (date == null) {
			return 0;
		}
		synchronized (calendar) {
			calendar.setTime(date);

			return calendar.get(Calendar.HOUR_OF_DAY);
		}
	}

	/**
	 * 获取date所在月的第一天
	 * 
	 * @param now
	 * @return
	 */
	public static Date getBeginDateOfMonth(Date now) {

		synchronized (calendar) {

			calendar.setTime(now);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
	}

	/**
	 * 为date添加指定小时，返回设置好的日期
	 * 
	 * @param now
	 * @return
	 */
	public static Date setHours(Date date, int hours) {

		synchronized (calendar) {
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, hours);
			return calendar.getTime();
		}
	}

	/**
	 * 获取间隔多少分钟的时间 如果传入的date为空，就返回空
	 * 
	 * @param now
	 * @return
	 */
	public static Date addMinutes(Date date, int minutes) {
		if (date == null) {
			return null;
		}
		synchronized (calendar) {
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, minutes);
			return calendar.getTime();
		}
	}

	/**
	 * 返回指定间隔 的日期
	 * 
	 * @param now
	 * @return
	 */
	public static Date getDateByDateAmount(Date date, int amount) {

		synchronized (calendar) {
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_YEAR, amount);

			return calendar.getTime();
		}
	}

	/**
	 * 添加指定的小时，返回添加好的日期
	 * 
	 * @param start
	 * @param i
	 * @return
	 */
	public static Date addHour(Date start, int i) {
		synchronized (calendar) {
			calendar.setTime(start);
			calendar.add(Calendar.HOUR_OF_DAY, i);
			return calendar.getTime();
		}
	}

	/**
	 * 获取两个日期的分钟间隔
	 * 
	 * @param lastReportTime
	 * @param now
	 */
	public static double getDurationMinutes(Date start, Date end) {

		if (start == null || end == null) {
			return 0;
		}
		synchronized (lock) {
			return (end.getTime() - start.getTime()) / 60000;
		}
	}

	/**
	 * 获取两个日期的秒数间隔
	 * 
	 * @param lastReportTime
	 * @param now
	 */
	public static double getDurationSeconds(Date start, Date end) {

		if (start == null || end == null) {
			return 0;
		}
		synchronized (lock) {
			return (end.getTime() - start.getTime()) / 1000;
		}
	}

	/**
	 * 获取date 的小时long形式 如：2013120613
	 * 
	 * @param seeAt
	 * @return
	 */
	public static Long getDateHour(Date date) {
		if (date == null) {
			return null;
		}
		synchronized (dateFormatForHour) {
			String hourStr = dateFormatForHour.format(date);
			return Long.parseLong(hourStr);
		}
	}

	/**
	 * 获取date 的long类型，默认是GMT 标准的long 时间
	 * 
	 * @param seeAt
	 * @return
	 */
	public static Long getTimeMillis(Date date, String type) {
		if (date == null) {
			return null;
		}
		synchronized (calendar) {

			calendar.setTimeZone(TimeZone.getTimeZone(type));
			return calendar.getTime().getTime();
		}
	}

	/**
	 * 获取date间隔between小时的 时间的小时long形式 如：2013120613
	 * 
	 * @return
	 */
	public static Date getNHour(Date date, int between) {
		if (date == null) {
			return null;
		}

		synchronized (calendar) {

			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, between);

			return calendar.getTime();
		}
	}

	/**
	 * 获取date间隔between小时的 时间的小时long形式 如：2013120613
	 * 
	 * @return
	 */
	public static long getDateHour(Date date, int between) {
		long hour = 0;
		if (date == null) {
			return hour;
		}

		synchronized (calendar) {

			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, between);

			String hourStr = dateFormatForHour.format(calendar.getTime());
			return Long.parseLong(hourStr);
		}
	}

	/**
	 * get Hour list
	 * 
	 * @param date
	 * @param between
	 * @return
	 * @throws ParseException
	 */
	public static List<Long> getHourList(String hour) throws ParseException {
		List<Long> hourList = null;
		if (hour == null || hour.trim().equals(""))
			return new ArrayList<Long>(0);
		String[] strs = hour.split("~");
		String beginHour = strs[0];
		String endHour = null;
		if (strs.length >= 2) {
			endHour = strs[1];
		}
		if (endHour == null)
			endHour = beginHour;
		hourList = new ArrayList<Long>();
		synchronized (calendar) {
			Date date = dateFormatForHour.parse(hour);
			calendar.setTime(date);
			Long h = null;
			do {
				h = Long.valueOf(dateFormatForHour.format(calendar.getTime()));
				hourList.add(h);
				calendar.add(Calendar.HOUR_OF_DAY, 1);
			} while (h < Long.valueOf(endHour));
			return hourList;
		}
	}

	/**
	 * get Hour list
	 * 
	 * @param date
	 * @param between
	 * @return
	 * @throws ParseException
	 */
	public static List<Date> getDateHourList(String hour) throws ParseException {
		List<Date> hourList = null;
		if (hour == null || hour.trim().equals(""))
			return new ArrayList<Date>(0);
		String[] strs = hour.split("~");
		String beginHour = strs[0];
		String endHour = null;
		if (strs.length >= 2) {
			endHour = strs[1];
		}
		if (endHour == null)
			endHour = beginHour;
		hourList = new ArrayList<Date>();
		synchronized (calendar) {
			Date date = dateFormatForHour.parse(hour);
			calendar.setTime(date);
			Long h = null;
			do {
				h = Long.valueOf(dateFormatForHour.format(calendar.getTime()));
				hourList.add(calendar.getTime());
				calendar.add(Calendar.HOUR_OF_DAY, 1);
			} while (h < Long.valueOf(endHour));
			return hourList;
		}
	}

	/**
	 * 获取date间隔between小时的 时间的小时long形式 如：2013120613
	 * 
	 * @return
	 */
	public static Date getDateHourForDate(Date date, int between) {
		if (date == null) {
			return null;
		}

		synchronized (calendar) {
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, between);

			return calendar.getTime();
		}
	}

	/**
	 * 获取当前时间的小时的开始
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeginHourTime(Date date) {

		if (date == null) {
			return null;
		}
		synchronized (calendar) {
			calendar.setTime(date);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
	}

	/**
	 * 获取输入时间下小时的开始
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNHourDay(Date date, int n) {

		if (date == null) {
			return null;
		}
		synchronized (calendar) {
			calendar.setTime(date);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.HOUR_OF_DAY, n);
			return calendar.getTime();
		}
	}

	/**
	 * 获取输入时间下小时的开始
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNBeginHourLast(Date date) {

		if (date == null) {
			return null;
		}
		synchronized (calendar) {
			calendar.setTime(date);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			return calendar.getTime();
		}
	}

	/**
	 * 获取当前时间 的小时的结束
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndHourTime(Date date) {

		if (date == null) {
			return null;
		}
		synchronized (calendar) {
			calendar.setTime(date);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			return calendar.getTime();
		}
	}

	public static boolean isSameDay(Date day, Date compareDay) {
		synchronized (calendar) {
			calendar.setTime(day);
			int day1 = calendar.get(Calendar.DAY_OF_MONTH);
			calendar.setTime(compareDay);
			int day2 = calendar.get(Calendar.DAY_OF_MONTH);
			return day1 == day2;
		}
	}

	public static void main(String[] args) throws Exception {

		// List<Long> hourList =
		// DateTimeUtil.getHourList("2013102210~2013121823");
		// for (Long hour : hourList)
		// System.out.println(hour);
		Date d = getNHourDay(new Date(), 0);
		System.out.println(d);

		System.out.println(getNBeginHourLast(d));
	}

	/**
	 * 把day 的天，设置给nowDay
	 * 
	 * @param noonStartTime
	 * @param day
	 * @return Date
	 */
	public static Date setDay(Date nowDay, Date day) {

		synchronized (calendar) {
			calendar.setTime(nowDay);

			Calendar calendar_new = Calendar.getInstance();
			calendar_new.setTime(day);

			calendar.set(Calendar.YEAR, calendar_new.get(Calendar.YEAR));
			calendar.set(Calendar.MONTH, calendar_new.get(Calendar.MONTH));
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar_new.get(Calendar.DAY_OF_MONTH));

			return calendar.getTime();
		}
	}

	/**
	 * @param day
	 * @param i
	 * @return Date
	 */
	public static Date setHour(Date day, int hour) {

		synchronized (calendar) {
			calendar.setTime(day);
			calendar.set(Calendar.HOUR_OF_DAY, hour);

			return calendar.getTime();
		}
	}

	/**
	 * @param day
	 * @param noonStartTime
	 * @return Date
	 */
	public static Date setTime(Date nowDay, Date day) {
		synchronized (calendar) {
			calendar.setTime(nowDay);

			Calendar calendar_new = Calendar.getInstance();
			calendar_new.setTime(day);

			calendar.set(Calendar.HOUR_OF_DAY, calendar_new.get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, calendar_new.get(Calendar.MINUTE));
			calendar.set(Calendar.SECOND,calendar_new.get(Calendar.SECOND));

			return calendar.getTime();
		}
	}

}
