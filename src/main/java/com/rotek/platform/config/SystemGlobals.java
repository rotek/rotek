/**
* @FileName: SystemGlobals.java
* @Package com.rotek.struts.utils
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-3 下午04:13:43
* @version V1.0
*/
package com.rotek.platform.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: SystemGlobals
 * @Description: 操作配置文件的工具类
 * @author chenwenpeng
 * @date 2013-5-3 下午04:13:43
 *
 */
public class SystemGlobals {

	/**@Field the Properties preferences*/
	private static Properties preferences = new Properties();
	/**
	* @Title: loadConfig
	* @Description:加载配置文件
	* @param @param trim
	* @return void
	* @throws
	*/
	public static void loadConfig(String file_name) {
		try {
			InputStream is = SystemGlobals.class.getClassLoader().getResourceAsStream(file_name);
			preferences.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("---------->加载系统配置文件失败!");
		}
	}

	/**
	* @Title: getPreference
	* @Description:
	* @param @param key
	* @param @return
	* @return String
	* @throws
	*/
	public static String getPreference(String key){
		if(StringUtils.isBlank(key)){
			return null;
		}
		String value = preferences.getProperty(key);
		if(StringUtils.isBlank(key)){
			return null;
		}
		return value;
	}

	/**
	* @Title: getPreference
	* @Description:
	* @param @param key
	* @param @param defaultValue
	* @param @return
	* @return String
	* @throws
	*/
	public static String getPreference(String key,String defaultValue){

		String value = getPreference(key);
		if(null == value){
			return defaultValue;
		}
		return value;
	}

	/**
	* @Title: getIntPreference
	* @Description:
	* @param @param key
	* @param @return
	* @return int
	* @throws
	*/
	public static int getIntPreference(String key,int defaultValue){

		String value = getPreference(key);
		if(null == value){
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	/**
	* @Title: getLongPreference
	* @Description:
	* @param @param key
	* @param @param defaultValue
	* @param @return
	* @return long
	* @throws
	*/
	public static long getLongPreference(String key,long defaultValue){
		String value = getPreference(key);
		if(null == value){
			return defaultValue;
		}
		return Long.parseLong(value);
	}

	/**
	* @Title: getDoublePreference
	* @Description:
	* @param @param key
	* @param @param defaultValue
	* @param @return
	* @return double
	* @throws
	*/
	public static double getDoublePreference(String key,double defaultValue){

		String value = getPreference(key);
		if(null == value){
			return defaultValue;
		}
		return Double.parseDouble(value);
	}
}
