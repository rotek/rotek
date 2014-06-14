/**
 * @FileName: ButtonEntity.java
 * @Package com.rotek.entity
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-6-3 上午09:25:15
 * @version V1.0
 */
package com.rotek.entity;

import java.io.Serializable;

/**
 * @author chenwenpeng
 * 
 */
public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3098992146592550230L;

	/**
	 * 有效
	 */
	public static final int STATUS_ENABLED = 1;
	/**
	 * 无效
	 */
	public static final int STATUS_UN_ENABLED = 2;
}
