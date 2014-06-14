/**
* @FileName: NotEmpty.java
* @Package com.cta.platform.persistence.annotation
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-6 上午09:00:48
* @version V1.0
*/
package com.cta.platform.persistence.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: NotEmpty
 * @Description: 不能为空
 * @author chenwenpeng
 * @date 2013-6-6 上午09:00:48
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface NotEmpty {
	/**
	* @Title: message
	* @Description:
	* @param @return
	* @return String
	* @throws
	*/
	String message() default "";
}
