/**
* @FileName: Phone.java
* @Package com.cta.platform.persistence.annotation
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-24 下午04:06:04
* @version V1.0
*/
package com.cta.platform.persistence.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Phone
 * @Description: 验证Email
 * @author chenwenpeng
 * @date 2013-6-24 下午04:06:04
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Email {

	/**
	* @Title: message
	* @Description:
	* @param @return
	* @return String
	* @throws
	*/
	String message() default "";
}
