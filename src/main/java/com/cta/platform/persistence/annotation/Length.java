/**
* @FileName: Length.java
* @Package com.cta.platform.persistence.annotation
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-6 上午08:30:05
* @version V1.0
*/
package com.cta.platform.persistence.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Length
 * @Description: 验证长度
 * @author chenwenpeng
 * @date 2013-6-6 上午08:30:05
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Length {
	/**
	* @Title: minLength
	* @Description:
	* @param @return
	* @return int
	* @throws
	*/
	int minLength() default -1;
	/**
	* @Title: maxLength
	* @Description:
	* @param @return
	* @return int
	* @throws
	*/
	int maxLength() default -1;

	/**
	* @Title: message
	* @Description:
	* @param @return
	* @return String
	* @throws
	*/
	String message() default "";
}
