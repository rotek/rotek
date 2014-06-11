/**
* @FileName: Column.java
* @Package com.cta.platform.persistence.annotation
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-10 上午11:28:21
* @version V1.0
*/
package com.cta.platform.persistence.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Column
 * @Description: 字段的注解
 * @author chenwenpeng
 * @date 2013-5-10 上午11:28:21
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface Column {

	/**
	* @Title: name
	* @Description:
	* @param @return
	* @return String
	* @throws
	*/
	String name() default "";
}
