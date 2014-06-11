/**
 * 
 */
package com.cta.platform.cache;

import java.io.Serializable;

/**@descriptions 缓存的具体操作类
 * @author chenwenpeng
 * @classname MemcachedEngine
 * @time 2013-4-29 下午8:43:14
 */
public interface CacheEngine {

	//失效时间
	/** The int EXPIRE_SECOND*/
	public static final int EXPIRE_SECOND = 1;
	/** The int EXPIRE_MINUTE*/
	public static final int EXPIRE_MINUTE = EXPIRE_SECOND * 60;
	/** The int EXPIER_HOUR*/
	public static final int EXPIER_HOUR = EXPIRE_MINUTE * 60;
	/** The int EXPIRE_DAY*/
	public static final int EXPIRE_DAY = EXPIER_HOUR * 24;
	/** The int EXPIRE_WEEK*/
	public static final int EXPIRE_WEEK = EXPIRE_DAY * 7;
	
	/** The int EXPIRE_DEFAULT*/
	public static final int EXPIRE_DEFAULT = EXPIER_HOUR * 3;
	
	/**
	* @Title: init
	* @Description: 
	* @param  
	* @return void 
	* @throws 
	*/ 
	public void init();
	
	/**
	* @Title: stop
	* @Description: 
	* @param  
	* @return void 
	* @throws 
	*/ 
	public void stop();
	
	
	/**
	* @Title: add
	* @Description: 
	* @param @param key
	* @param @param value 
	* @return void 
	* @throws 
	*/ 
	public void add(Serializable key,Object value);
	
	/**
	* @Title: add
	* @Description: 
	* @param @param key
	* @param @param value
	* @param @param expire 
	* @return void 
	* @throws 
	*/ 
	public void add(Serializable key,Object value,long expire);
	
	/**
	* @Title: get
	* @Description: 
	* @param @param key
	* @param @return 
	* @return Object 
	* @throws 
	*/ 
	public Object get(Serializable key);
	
	/**
	* @Title: remove
	* @Description: 
	* @param @param key 
	* @return void 
	* @throws 
	*/ 
	public void remove(Serializable key);
}
