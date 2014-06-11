/**
* @FileName: EntityUtils.java
* @Package com.cta.platform.persistence.db
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-16 下午05:09:43
* @version V1.0
*/
package com.cta.platform.util;

import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: EntityUtils
 * @Description: 实体类的工具类
 * @author chenwenpeng
 * @date 2013-5-16 下午05:09:43
 *
 */
public class EntityUtils {

    /**
    * @Title: getEntitySuperClass
    * @Description: 获取实体类的父类
    * @param @param <T>
    * @param @param clazz
    * @param @return
    * @return Class<?>
    * @throws
    */
    public static <T> Class<?> getEntitySuperClass(Class<? extends T> clazz) {
	Class<?> superClass = clazz;
	while (superClass != null && !"java.lang.Object".equals(superClass.getName())) {
	    if (superClass.isAnnotationPresent(Table.class)) {
	    	return superClass;
	    }
	    superClass = superClass.getSuperclass();
	}
		return null;
    }
}
