/**
* @FileName: DBOption.java
* @Package com.cta.platform.persistence.db
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-7 上午09:08:18
* @version V1.0
*/
package com.cta.platform.persistence.db;

/**
 * @ClassName: DBOption
 * @Description: 数据库类型
 * @author chenwenpeng
 * @date 2013-5-7 上午09:08:18
 *
 */
public class DBOption {

	/**@Field the int DB_MYSQL*/
	public static final int DB_MYSQL = 1;

	/**@Field the int DB_ORACLE*/
	public static final int DB_ORACLE = 2;

	/**@Field the int DB_SQLSERVER*/
	public static final int DB_SQLSERVER = 3;

	/**@Field the String DB_NAME_MYSQL*/
	public static final String DB_NAME_MYSQL = "mysql";

	/**@Field the String DB_NAME_ORACLE*/
	public static final String DB_NAME_ORACLE = "oracle";

	/**@Field the String DB_NAME_SQLSERVER*/
	public static final String DB_NAME_SQLSERVER = "sqlserver";
}
