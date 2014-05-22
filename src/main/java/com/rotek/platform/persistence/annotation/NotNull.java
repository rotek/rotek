/**
* @FileName: NotNull.java
* @Package com.rotek.platform.persistence.annotation
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-8 下午03:45:36
* @version V1.0
*/
package com.rotek.platform.persistence.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: NotNull
 * @Description: 不能为null
 * @author chenwenpeng
 * @date 2013-8-8 下午03:45:36
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface NotNull {

	/**
	* @Title: message
	* @Description:
	* @param @return
	* @return String
	* @throws
	*/
	String message() default "";
}
