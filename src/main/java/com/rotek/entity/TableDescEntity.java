/**
* @FileName: ButtonEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-3 上午09:25:15
* @version V1.0
*/
package com.rotek.entity;

/**
* @ClassName:TableDescEntity
* @Description: 表结构描述信息
* @Author WangJuZhu
* @date 2014年7月25日 下午5:22:14
* @Version:1.1.0
*/
public class TableDescEntity extends BaseEntity{

	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = 103654789244563236L;
	
	//表名称
	private String tableName;
	
	//列名称
	private String columnName;
	
	//列类型
	private String columnType;
	
	//列注释
	private String columnComment;

	/** @return tableName */
	public String getTableName() {
		return tableName;
	}

	/** @param tableName tableName to set */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/** @return columnName */
	public String getColumnName() {
		return columnName;
	}

	/** @param columnName columnName to set */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/** @return columnType */
	public String getColumnType() {
		return columnType;
	}

	/** @param columnType columnType to set */
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	/** @return columnComment */
	public String getColumnComment() {
		return columnComment;
	}

	/** @param columnComment columnComment to set */
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

}
