/**
* @FileName: PropertyMapping.java
* @Package com.rotek.platform.persistence.db
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-10 下午03:52:32
* @version V1.0
*/
package com.rotek.platform.persistence.db;

import java.io.Serializable;

/**
 * @ClassName: PropertyMapping
 * @Description:
 * @author chenwenpeng
 * @date 2013-5-10 下午03:52:32
 *
 */
public class PropertyMapping implements Serializable{
	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = 1L;
	/**@Field the Class<?> propertyClass 字段对应的class*/
	private Class<?> propertyClass;
	/**@Field the String propertyName 字段名*/
	private String propertyName;
	/**@Field the boolean isPrimaryKey*/
	private boolean primaryKey = false;
	/**@Field the int generateStrategy 主键生成策略*/
	private int generateStrategy;
	/**@Field the ColumnMapping columnMapping*/
	private ColumnMapping columnMapping;
	/** The Integer lobType 是否为大文本*/
	private Integer lobType = null;

	public Integer getLobType() {
		return lobType;
	}
	public void setLobType(Integer lobType) {
		this.lobType = lobType;
	}
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	public Class<?> getPropertyClass() {
		return propertyClass;
	}
	public void setPropertyClass(Class<?> propertyClass) {
		this.propertyClass = propertyClass;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public int getGenerateStrategy() {
		return generateStrategy;
	}
	public void setGenerateStrategy(int generateStrategy) {
		this.generateStrategy = generateStrategy;
	}
	public ColumnMapping getColumnMapping() {
		return columnMapping;
	}
	public void setColumnMapping(ColumnMapping columnMapping) {
		this.columnMapping = columnMapping;
	}
}
