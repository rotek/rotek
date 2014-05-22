/**
* @FileName: ColumnMapping.java
* @Package com.rotek.platform.persistence.db
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-10 下午04:12:24
* @version V1.0
*/
package com.rotek.platform.persistence.db;

import java.io.Serializable;

/**
 * @ClassName: ColumnMapping
 * @Description: 注解对应的mapping
 * @author chenwenpeng
 * @date 2013-5-10 下午04:12:24
 *
 */
public class ColumnMapping implements Serializable{

	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = 1L;

	/**@Field the String columnName 注解名字*/
	private String columnName;
	/**@Field the boolean lobLazy*/
	private boolean lobLazy = true;
	/**@Field the int lobType*/
	private Integer lobType = null;



	public int getLobType() {
		return lobType;
	}
	public void setLobType(int lobType) {
		this.lobType = lobType;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public boolean isLobLazy() {
		return lobLazy;
	}
	public void setLobLazy(boolean lobLazy) {
		this.lobLazy = lobLazy;
	}
}
