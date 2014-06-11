/**
* @FileName: EntityMapping.java
* @Package com.cta.platform.persistence.db
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-15 下午01:23:32
* @version V1.0
*/
package com.cta.platform.persistence.db;

/**
 * @ClassName: EntityMapping
 * @Description: 特殊的实体类映射
 * @author chenwenpeng
 * @date 2013-5-15 下午01:23:32
 *
 */
public class EntityMapping extends ObjectMapping{

	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = 144778833322328013L;
	/**@Field the String tableName*/
	private String tableName;
	/**@Field the PropertyMapping idPropertyMapping*/
	private PropertyMapping idPropertyMapping;


	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public PropertyMapping getIdPropertyMapping() {
		return idPropertyMapping;
	}
	public void setIdPropertyMapping(PropertyMapping idPropertyMapping) {
		this.idPropertyMapping = idPropertyMapping;
	}
}
