/**
* @FileName: Size.java
* @Package com.cta.platform.persistence.annotation
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-6 上午08:35:12
* @version V1.0
*/
package com.cta.platform.persistence.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Size
 * @Description: 验证数值大小
 * @author chenwenpeng
 * @date 2013-6-6 上午08:35:12
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Size {
	/**
	* @Title: minIntSize
	* @Description:
	* @param @return
	* @return int
	* @throws
	*/
	int minIntSize() default Integer.MIN_VALUE;
	/**
	* @Title: maxIntSize
	* @Description:
	* @param @return
	* @return int
	* @throws
	*/
	int maxIntSize() default Integer.MAX_VALUE;

	/**
	* @Title: minLongSize
	* @Description:
	* @param @return
	* @return long
	* @throws
	*/
	long minLongSize() default Long.MIN_VALUE;
	/**
	* @Title: maxLongSize
	* @Description:
	* @param @return
	* @return long
	* @throws
	*/
	long maxLongSize() default Long.MAX_VALUE;

	/**
	* @Title: minDoubleSize
	* @Description:
	* @param @return
	* @return double
	* @throws
	*/
	double minDoubleSize() default Double.MIN_VALUE;
	/**
	* @Title: maxDoubleSize
	* @Description:
	* @param @return
	* @return double
	* @throws
	*/
	double maxDoubleSize() default Double.MAX_VALUE;

	/**
	* @Title: message
	* @Description:
	* @param @return
	* @return String
	* @throws
	*/
	String message() default "";
}
