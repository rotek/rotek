/**
* @FileName: LobType.java
* @Package com.cta.platform.persistence.meta
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-9 下午01:09:25
* @version V1.0
*/
package com.cta.platform.constant;

/**
 * @ClassName: LobType
 * @Description: lob的类型,不允许往数据库中插入文件，只允许把大文本格式的CLOB 和 Text
 * @author chenwenpeng
 * @date 2013-5-9 下午01:09:25
 *
 */
public class LobType {

	/**@Field the int CLOB oracle 使用*/
	public static final int CLOB = 0;
	/**@Field the int TEXT mysql使用*/
	public static final int TEXT = 1;

	/**@Field the String CLOB_NAME*/
	public static final String CLOB_NAME = "CLOB";
	/**@Field the String TEXT_NAME*/
	public static final String TEXT_NAME = "TEXT";
}
