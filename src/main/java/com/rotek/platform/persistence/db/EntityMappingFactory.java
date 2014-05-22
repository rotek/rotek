/**
* @FileName: EntityMappingFactory.java
* @Package com.rotek.platform.persistence.db
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-10 下午04:34:26
* @version V1.0
*/
package com.rotek.platform.persistence.db;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.rotek.platform.constant.LobType;
import com.rotek.platform.persistence.annotation.Clob;
import com.rotek.platform.persistence.annotation.Column;
import com.rotek.platform.persistence.annotation.ID;
import com.rotek.platform.persistence.annotation.Table;
import com.rotek.platform.util.EntityUtils;

/**
 * @ClassName: EntityMappingFactory
 * @Description: ObjectMapping的工厂类
 * @author chenwenpeng
 * @date 2013-5-10 下午04:34:26
 *
 */
public class EntityMappingFactory {

	/**@Field the Map<String,EntityMapping> objectMappings*/
	private static final Map<String,EntityMapping> entityMappings = new HashMap<String,EntityMapping>();
	/**@Field the Map<String,EntityMappingFactory> entityMappings*/
	private static final Map<String,ObjectMapping> objectMappings = new HashMap<String,ObjectMapping>();
	/**
	* @Title: getObjectMapping
	* @Description: 获取类对应的objectmapping
	* @param @param clazz
	* @param @return
	* @return ObjectMapping
	* @throws
	*/
	public static <T> ObjectMapping getObjectMapping(Class<T> clazz) {
		ObjectMapping mapping = objectMappings.get(clazz.getName());
		if(null != mapping){
//			return mapping;
		}
		mapping = new ObjectMapping();
		loadObjectMapping(mapping,clazz);

		//objectMappigs 中添加objectMapping
		objectMappings.put(clazz.getName(), mapping);
		return mapping;
	}

	/**
	* @Title: loadObjectMapping
	* @Description:
	* @param @param mapping
	* @param @param clazz
	* @return void
	* @throws
	*/
	private static <T> void loadObjectMapping(ObjectMapping mapping,
			Class<T> clazz) {

		Map<String,PropertyMapping> propertyMappings = new HashMap<String,PropertyMapping>();
		//加载propertyMapping
		loadPropertyMapping(propertyMappings,clazz);

		mapping.setEntityClass(clazz);
		mapping.setPropertyMapping(propertyMappings);
	}

	/**
	* @Title: getTableName
	* @Description: 根据实体类的class获取对应的表名
	* @param @param clazz
	* @param @return
	* @return String
	* @throws
	*/
	public static <T> String getTableName(Class<T> clazz){
		String tableName = null;
		if(clazz.isAnnotationPresent(Table.class)){
			Annotation annotation = clazz.getAnnotation(Table.class);
			tableName = ((Table)annotation).name();
		}else {
			String className = clazz.getName();
			if(StringUtils.isNotBlank(className)){
				tableName = className.substring(className.lastIndexOf(".")+1, className.length());
			}
		}
		return tableName;
	}

	/**
	* @Title: loadPropertyMapping
	* @Description: 加载 propertyMapping
	* @param @param propertyMapping
	* @param @param clazz
	* @return void
	* @throws
	*/
	private static <T> void loadPropertyMapping(
			Map<String, PropertyMapping> propertyMappings,
			Class<T> clazz) {
		//一个字段的mapping
		PropertyMapping propertyMapping = null;
		Class<?> c = clazz;
		while (c != null && !c.getName().equals(Object.class.getName())) {
		    for(Field filed : c.getDeclaredFields()){
		    	propertyMapping = new PropertyMapping();

		    	int modifier = filed.getModifiers();
		    	if(Modifier.isStatic(modifier) || Modifier.isFinal(modifier)){
		    		continue;
		    	}
				String fieldName = filed.getName();
				ColumnMapping columnMapping = new ColumnMapping();
				//加载属性对应的注解信息
				loadColumnMapping(propertyMapping,columnMapping,filed);
				propertyMapping.setPropertyName(fieldName);
				propertyMapping.setColumnMapping(columnMapping);

				propertyMappings.put(fieldName, propertyMapping);
		    }
		    c = c.getSuperclass();
		}
	}

	/**
	 * @param propertyMapping
	* @Title: loadColumnMapping
	* @Description: 获取注解信息
	* @param @param columnMappings
	* @param @param filed
	* @return void
	* @throws
	*/
	private static void loadColumnMapping(PropertyMapping propertyMapping, ColumnMapping columnMapping,
			Field field) {
		//获取注解信息
		if(field.isAnnotationPresent(ID.class)){
			ID annotation = field.getAnnotation(ID.class);
			int strategyType = annotation.strategy();
			propertyMapping.setPrimaryKey(true);
			propertyMapping.setGenerateStrategy(strategyType);
		}
		if(field.isAnnotationPresent(Column.class)){
			Column annotation = field.getAnnotation(Column.class);
			String name = annotation.name();
			columnMapping.setColumnName(name);
		}

		if(field.isAnnotationPresent(Clob.class)){
			Clob annotation = field.getAnnotation(Clob.class);
			if(!annotation.lazy()){
				propertyMapping.setLobType(LobType.TEXT);
			}
		}
	}


	/**
	* @Title: getEntityMapping
	* @Description:
	* @param @param <T>
	* @param @param clazz
	* @param @return
	* @return EntityMapping
	* @throws
	*/
	public static <T> EntityMapping getEntityMapping(Class<T> clazz){

		EntityMapping mapping = (EntityMapping) entityMappings.get(clazz.getName());
		if(null != mapping){
//			return mapping;
		}
		mapping = new EntityMapping();
		loadEntityMapping(mapping,clazz);

		entityMappings.put(clazz.getName(), mapping);
		return mapping;
	}

	/**
	* @Title: loadEntityMapping
	* @Description: 加载实体类的mapping
	* @param @param <T>
	* @param @param entityMapping
	* @param @param clazz
	* @return void
	* @throws
	*/
	public static <T> void loadEntityMapping(EntityMapping entityMapping,Class<T> clazz){
		Class<?> superClass = EntityUtils.getEntitySuperClass(clazz);
		String tableName = getTableName(superClass);
		entityMapping.setTableName(tableName);

		//加载通用的mapping信息
		loadObjectMapping(entityMapping,clazz);

		//获取idPropertyMapping
		for(PropertyMapping propertyMapping : entityMapping.getPropertyMapping().values()){
			if(propertyMapping.isPrimaryKey()){
				entityMapping.setIdPropertyMapping(propertyMapping);
				return;
			}
		}
	}
}
