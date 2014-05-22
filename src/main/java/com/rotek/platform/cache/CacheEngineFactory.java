/**
 *
 */
package com.rotek.platform.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.rotek.platform.config.SystemGlobals;

/**@descriptions 缓存管理器的 工厂类
 * @author chenwenpeng
 * @classname CacheEngineFactory
 * @time 2013-4-29 下午8:50:25
 */
public class CacheEngineFactory {
	/** The Constant logger. */
    private static final Logger logger = Logger.getLogger(CacheEngineFactory.class);
	/** The Map<String,CacheEngine> cacheEngineMap*/
	private static Map<String,CacheEngine> cacheEngineMap = new ConcurrentHashMap<String,CacheEngine>();
	/**@Field the String DEFAULT_ENGINE_NAME*/
	private static final String DEFAULT_ENGINE_NAME = "default_engine";

	/**
	* @Title: getEngine
	* @Description:
	* @param @return
	* @return CacheEngine
	* @throws
	*/
	public static CacheEngine getEngine(){
		String cacheEngineClass = SystemGlobals.getPreference("cache.implement");
		if(StringUtils.isBlank(cacheEngineClass)){
			cacheEngineClass = "com.rotek.platform.cache.MemcachedEngine";
		}
		return getEngine(DEFAULT_ENGINE_NAME,cacheEngineClass);
	}
	/**
	 *getEngine: 获取缓存引擎
	 *@param name
	 *@param cacheEngineClass
	 *@return
	 *
	 * @return the CacheEngine
	 */
	public static CacheEngine getEngine(String name,String cacheEngineClass){
		CacheEngine cacheEngine = cacheEngineMap.get(name);
		if(null != cacheEngine){
			return cacheEngine;
		}
		try {
			if(!StringUtils.isBlank(cacheEngineClass)){
				//创建并初始化缓存引擎
				cacheEngine = (CacheEngine) Class.forName(cacheEngineClass).newInstance();
				cacheEngine.init();
				//引擎放到容器中
				cacheEngineMap.put(name, cacheEngine);
			}
		} catch (Exception e) {
			logger.error("CacheEngineFactory Exception:", e);
			return null;
		}
		return cacheEngine;
	}
}
