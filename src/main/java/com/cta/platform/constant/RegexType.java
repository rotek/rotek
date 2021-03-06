/**
* @FileName: RegexType.java
* @Package com.cta.constant
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-24 下午04:23:36
* @version V1.0
*/
package com.cta.platform.constant;

/**
 * @ClassName: RegexType
 * @Description: 正则表达式
 * @author chenwenpeng
 * @date 2013-6-24 下午04:23:36
 *
 */
public class RegexType {

	/**@Field the String REG_PHONE 验证电话*/
	public static final String REGEX_PHONE = "^1[3|4|5|8][0-9]\\d{8}$";
	public static final String REGEX_EMAIL = "^[a-zA-Z0-9_\\.]+@[a-zA-Z0-9-]+[\\.a-zA-Z]+$";
}
