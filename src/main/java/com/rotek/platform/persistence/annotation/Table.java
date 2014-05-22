/**
* @FileName: Table.java
* @Package com.rotek.platform.persistence.annotation
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-10 上午10:53:50
* @version V1.0
*/
package com.rotek.platform.persistence.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @ClassName: Table
 * @Description: 数据库注解，不可以被继承
 * @author chenwenpeng
 * @date 2013-5-10 上午10:53:50
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface Table {

	/**
	* @Title: name
	* @Description:
	* @param @return
	* @return String
	* @throws
	*/
	String name() default "";
}
