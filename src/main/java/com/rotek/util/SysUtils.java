/**
* @FileName: SysUtils.java
* @Package com.rotek.util
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-21 下午06:13:44
* @version V1.0
*/
package com.rotek.util;

import java.util.Random;

/**
 * @ClassName: SysUtils
 * @Description:
 * @author chenwenpeng
 * @date 2013-6-21 下午06:13:44
 *
 */
public class SysUtils {

	/**
	* @Title: getRandom
	* @Description: 获取 count 个随机数
	* @param count
	* @return
	* @return String
	* @throws
	*/
	public static String getRandom(int count){
		StringBuilder number = new StringBuilder();
		Random random = new Random();
		for(int i = 0;i<count;i++){
			number.append(random.nextInt(10));
		}
		return number.toString();
	}
}
