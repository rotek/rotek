/**
* @FileName: DateUtils.java
* @Package com.rotek.util
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-9 下午03:45:42
* @version V1.0
*/
package com.rotek.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: DateUtils
 * @Description: 日期工具类
 * @author chenwenpeng
 * @date 2013-7-9 下午03:45:42
 *
 */
public class DateUtils {

	/**
	* @Title: formatDate
	* @Description:
	* @return
	* @return String
	* @throws
	*/
	public static String formatDate(){
		Date date = new Date();
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	/**
	 * @Title: formatTime
	 * @Description:
	 * @return
	 * @return String
	 * @throws
	 */
	public static String formatTime(){
		Date date = new Date();
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}

	/**
	 * @Title: formatTime
	 * @Description:
	 * @return
	 * @return String
	 * @throws
	 */
	public static String formatTime(Date date){
		if(null == date){
			return null;
		}
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}
	/**
	* @Title: formatDateTime
	* @Description:
	* @return
	* @return String
	* @throws
	*/
	public static String formatDateTime(){
		Date date = new Date();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	/**
	* @Title: formatDate
	* @Description:
	* @param date
	* @return
	* @return String
	* @throws
	*/
	public static String formatDate(Date date){
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	/**
	* @Title: formatDateTime
	* @Description:
	* @param date
	* @return
	* @return String
	* @throws
	*/
	public static String formatDateTime(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * @throws ParseException
	* @Title: parseTime
	* @Description: 解析时间
	* @param str_time
	* @return
	* @return String
	* @throws
	*/
	public static Date parseTime(String str_time) throws ParseException{
		if(StringUtils.isBlank(str_time)){
			return null;
		}
		return new SimpleDateFormat("HH:mm:ss").parse(str_time);
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
	public static Date parseDateTimeByTime(String str_time) throws ParseException{
		if(StringUtils.isBlank(str_time)){
			return null;
		}
		Date now = new Date();
		Calendar calendar_datetime = Calendar.getInstance();
		calendar_datetime.setTime(now);

		Date time = DateUtils.parseTime(str_time);
		Calendar calendar_time = Calendar.getInstance();
		calendar_time.setTime(time);

		calendar_datetime.set(calendar_datetime.get(Calendar.YEAR), calendar_datetime.get(Calendar.MONTH), calendar_datetime.get(Calendar.DAY_OF_MONTH), calendar_time.get(Calendar.HOUR_OF_DAY), calendar_time.get(Calendar.MINUTE), calendar_time.get(Calendar.SECOND));
		return calendar_datetime.getTime();
	}

	/**
	* @Title: getDateForQuery
	* @Description: 获取 2013-07-05 00:00:00，这种类型的用于查询的时间
	* @param date
	* @return
	* @return Date
	* @throws
	*/
	public static Date getDateForQuery(Date date){
		if(null == date){
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		return calendar.getTime();
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
	public static boolean compareDateTime(Date date,Date compareTo){
		if(null == date || null == compareTo){
			return false;
		}
		return date.getTime() > compareTo.getTime();
	}

	public static void main(String[] args) throws ParseException {


//		System.out.println(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));

		System.out.println(DateUtils.getDateForQuery(new Date()));
	}

}
