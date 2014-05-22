/**
* @FileName: MemCacheManager.java
* @Package com.rotek.platform.cache
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-20 上午10:23:40
* @version V1.0
*/
package com.rotek.platform.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: MemCacheManager
 * @Description: memcache 的管理类，供程序里面调用
 * @author chenwenpeng
 * @date 2013-5-20 上午10:23:40
 *
 */
public class MemCacheManager {

	/**@Field the String SITE_CACHE 前台引擎管理器*/
	public static final String SITE_CACHEMANAGER = "site_cache";
	/**@Field the String ADMIN_CACHE 后台引擎管理器*/
	public static final String ADMIN_CACHEMANAGER = "admin_cache";
	/**@Field the String CACHEMANAGER_NAME 管理引擎的名称*/
	private static String CACHEMANAGER_NAME = null;
	/**@Field the Map<String,MemCacheManager> factory 管理器的容器*/
	public static final Map<String,MemCacheManager> factory = new ConcurrentHashMap<String, MemCacheManager>();
	/**@Field the String DEFAULT_KEY_NAME*/
	private static final String DEFAULT_KEY_NAME = "default_key";
	/**
	* @Title: getMemCacheManager
	* @Description: 获取引擎，引擎名称推荐为：SITE_CACHEMANAGER 或者 ADMIN_CACHEMANAGER 便于管理，且不能为空
	* @param @param managerName
	* @param @return
	* @return MemCacheManager
	* @throws
	*/
	public static MemCacheManager getMemCacheManager(String managerName){
		//设置引擎名称
		CACHEMANAGER_NAME = managerName;
		MemCacheManager memCacheManager = factory.get(CACHEMANAGER_NAME);
		if(null != memCacheManager){
			return memCacheManager;
		}
		memCacheManager = new MemCacheManager();
		factory.put(CACHEMANAGER_NAME, memCacheManager);
		return memCacheManager;
	}

	/**
	* @Title: add
	* @Description:
	* @param @param <T>
	* @param @param key
	* @param @param value
	* @param @param clazz
	* @param @param expire
	* @return void
	* @throws
	*/
	public <T> void add(String key,T value,Class<T> clazz,int expire){
		CacheEngine engine = CacheEngineFactory.getEngine();
		if(null != engine){
			key = buildKey(key, clazz);
			engine.add(key, value, expire);
		}
	}
	/**
	* @Title: add
	* @Description:添加或覆盖缓存
	* @param @param <T>
	* @param @param key
	* @param @param value
	* @param @param clazz
	* @return void
	* @throws
	*/
	public <T> void add(String key,T value,Class<T> clazz){
		int expire = CacheEngine.EXPIRE_DEFAULT;
		add(key, value, clazz, expire);
	}

	/**
	* @Title: get
	* @Description:添加或覆盖缓存
	* @param @param <T>
	* @param @param key
	* @param @param clazz
	* @param @return
	* @return T
	* @throws
	*/
	@SuppressWarnings("unchecked")
	public <T> T get(String key,Class<T> clazz){
		T value = null;
		CacheEngine engine = CacheEngineFactory.getEngine();
		if(null != engine){
			key = buildKey(key, clazz);
			value = (T) engine.get(key);
		}
		return value;
	}

	/**
	* @Title: remove
	* @Description: 删除缓存
	* @param @param <T>
	* @param @param key
	* @param @param clazz
	* @return void
	* @throws
	*/
	public <T> void remove(String key,Class<T> clazz){
		CacheEngine engine = CacheEngineFactory.getEngine();
		if(null != engine){
			key = buildKey(key, clazz);
			engine.remove(key);
		}
	}


	/**
	* @Title: buildKey
	* @Description: 获取key的命名
	* @param @param <T>
	* @param @param key
	* @param @param clazz
	* @param @return
	* @return String
	* @throws
	*/
	public <T> String buildKey(String key,Class<T> clazz){
		if(null == key || null == clazz){
			return DEFAULT_KEY_NAME;
		}
		return CACHEMANAGER_NAME + "_" + clazz.getName() + "_" + key;
	}
}
