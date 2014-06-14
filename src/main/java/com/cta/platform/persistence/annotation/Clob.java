/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.cta.platform.persistence.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: CLOB
 * @Description: CLOB的注解，ORACLE使用
 * @author chenwenpeng
 * @date 2013-5-12 下午2:25:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface Clob {

	/**
	* @Title: lazy
	* @Description:是否为懒加载
	* @param @return
	* @return boolean
	* @throws
	*/
	boolean lazy() default true;
}
