/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.cta.platform.persistence.db;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: EntityFactory
 * @Description:
 * @author chenwenpeng
 * @date 2013-5-12 下午12:56:01
 */
public class EntityFactory {

	/** The Map<String,Object> entitys*/
	private static final Map<String,Object> entitys = new HashMap<String,Object>();

	/**
	* @Title: createEntity
	* @Description: 创建实体类的对象
	* @param @param objectMapping
	* @param @return
	* @param @throws InstantiationException
	* @param @throws IllegalAccessException
	* @return Object
	* @throws
	*/
	public static Object createEntity(ObjectMapping objectMapping) throws InstantiationException, IllegalAccessException {
		Class<?> clazz = objectMapping.getEntityClass();
		if(null == clazz){
			return null;
		}
		return clazz.newInstance();
	}

	/**
	* @Title: createEntity
	* @Description:
	* @param @param clazz
	* @param @return
	* @param @throws InstantiationException
	* @param @throws IllegalAccessException
	* @return Object
	* @throws
	*/
	public static Object createEntity(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		if(null == clazz){
			return null;
		}
		return clazz.newInstance();
	}
}
