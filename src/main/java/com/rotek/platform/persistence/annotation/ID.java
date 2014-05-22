/**
* @FileName: ID.java
* @Package com.rotek.platform.annotation
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-10 上午10:41:40
* @version V1.0
*/
package com.rotek.platform.persistence.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.rotek.platform.constant.StrategyType;


/**
 * @ClassName: ID
 * @Description: 主键的注解
 * @author chenwenpeng
 * @date 2013-5-10 上午10:41:40
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface ID {

	/**
	* @Title: strategy
	* @Description:
	* @param @return
	* @return int
	* @throws
	*/
	int strategy() default StrategyType.IDENTITY;

}
