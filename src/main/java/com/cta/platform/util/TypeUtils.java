/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.cta.platform.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: TypeUtils
 * @Description: 检查类型的工具类
 * @author chenwenpeng
 * @date 2013-5-12 下午3:16:05
 */
public class TypeUtils {

	/** The Constant baseClassNames. */
	private static final String[] baseClassNames = new String[] {
			"java.lang.String", "int", "java.lang.Integer", "float",
			"java.lang.Float", "double", "java.lang.Double", "long",
			"java.lang.Long", "short", "java.lang.Short", "byte",
			"java.lang.Byte", "boolean", "java.lang.Boolean", 
			"java.util.Date","java.sql.Date", "java.util.Timestamp", 
			"java.sql.Timestamp","java.sql.Time","java.lang.BigDecimal" };
	
	/**
	* @Title: isBaseType
	* @Description: 检查是否是基本类型
	* @param @param clazz
	* @param @return 
	* @return boolean 
	* @throws 
	*/ 
	public static <T> boolean isBaseType(String className){
		if(StringUtils.isBlank(className)){
			return false;
		}
		return ArrayUtils.contains(baseClassNames, className);
	}
}
