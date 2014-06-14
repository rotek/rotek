/**
 * @FileName: CacheManager.java
 * @Package com.cta.platform.cache
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-5-20 上午10:23:40
 * @version V1.0
 */
package com.cta.platform.cache;

import redis.clients.jedis.ShardedJedis;

/**
 * @ClassName: MemCacheManager
 * @Description: memcache 的管理类，供程序里面调用
 * @author chenwenpeng
 * @date 2013-5-20 上午10:23:40
 * 
 */
public class RedisManager {

	private RedisEngine redisEngine;
	// 失效时间
	/** The int EXPIRE_SECOND */
	public static final int EXPIRE_SECOND = 1;
	/** The int EXPIRE_MINUTE */
	public static final int EXPIRE_MINUTE = EXPIRE_SECOND * 60;
	/** The int EXPIER_HOUR */
	public static final int EXPIER_HOUR = EXPIRE_MINUTE * 60;
	/** The int EXPIRE_DAY */
	public static final int EXPIRE_DAY = EXPIER_HOUR * 24;
	/** The int EXPIRE_WEEK */
	public static final int EXPIRE_WEEK = EXPIRE_DAY * 7;
	/** The int EXPIRE_DEFAULT */
	public static final int EXPIRE_DEFAULT = EXPIER_HOUR * 3;

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
	public <T> void add(String key, T value, Class<T> clazz, int expire) {

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
	public <T> void add(String key, T value, Class<T> clazz) {

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
	public <T> T get(String key, Class<T> clazz) {

		return null;
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
	public <T> void remove(String key, Class<T> clazz) {

	}

	public ShardedJedis getJedis() {
		return redisEngine.getJedis();
	}

	public void returnWriteResource(ShardedJedis jedis) {
		redisEngine.returnWriteResource(jedis);
	}

	public void returnBrokenResource(ShardedJedis jedis) {
		redisEngine.returnBrokenResource(jedis);
	}

	public RedisEngine getRedisEngine() {
		return redisEngine;
	}

	public void setRedisEngine(RedisEngine redisEngine) {
		this.redisEngine = redisEngine;
	}

}
