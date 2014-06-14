/**
* @FileName: ObjectMapping.java
* @Package com.cta.platform.persistence.db
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-10 下午03:45:38
* @version V1.0
*/
package com.cta.platform.persistence.db;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ObjectMapping
 * @Description: 一般的实体类映射
 * @author chenwenpeng
 * @date 2013-5-10 下午03:45:38
 *
 */
public class ObjectMapping implements Serializable{
	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = 144778838343829016L;
	/**@Field the Class<?> entityClass*/
	private Class<?> entityClass;
	/**@Field the Map<String,PropertyMapping>*/
	private Map<String,PropertyMapping> propertyMapping = new HashMap<String,PropertyMapping>();
	/** The String tableName*/
	private String tableName;

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Class<?> getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
	public Map<String, PropertyMapping> getPropertyMapping() {
		return propertyMapping;
	}
	public void setPropertyMapping(Map<String, PropertyMapping> propertyMapping) {
		this.propertyMapping = propertyMapping;
	}
}
