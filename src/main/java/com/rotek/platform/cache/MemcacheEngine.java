/**
 *
 */
package com.rotek.platform.cache;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.rotek.platform.config.SystemGlobals;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * @descriptions memcached的管理引擎
 * @author chenwenpeng
 * @classname MemcachedEngine
 * @time 2013-4-30 上午10:39:17
 */
public class MemcacheEngine implements CacheEngine {

	/**
	 * init: (non-Javadoc).
	 *
	 * @see com.mfw.util.cache.CacheEngine#init()
	 */
	@Override
	public void init() {
		String s = SystemGlobals.getPreference("cache.mem.servers");
		String[] a = s.split(",");

		String[] serverList = new String[a.length];
		Integer[] weights = new Integer[a.length];
		for (int i = 0; i < a.length; i++) {
			a[i] = a[i].trim();
			serverList[i] = a[i];
			weights[i] = new Integer(a[i].substring(a[i].lastIndexOf(":") + 1));
		}

		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(serverList);
		pool.setWeights(weights);

		s = SystemGlobals.getPreference("cache.mem.initConn");
		if (!StringUtils.isBlank(s))
			pool.setInitConn(Integer.parseInt(s));

		s = SystemGlobals.getPreference("cache.mem.maxConn");
		if (!StringUtils.isBlank(s))
			pool.setMaxConn(Integer.parseInt(s));

		s = SystemGlobals.getPreference("cache.mem.maxIdle");
		if (!StringUtils.isBlank(s))
			pool.setMaxIdle(Long.parseLong(s));

		s = SystemGlobals.getPreference("cache.mem.maintSleep");
		if (!StringUtils.isBlank(s))
			pool.setMaintSleep(Long.parseLong(s));

		s = SystemGlobals.getPreference("cache.mem.socketTO");
		if (!StringUtils.isBlank(s))
			pool.setSocketTO(Integer.parseInt(s));

		s = SystemGlobals.getPreference("cache.mem.socketConnectTO");
		if (!StringUtils.isBlank(s))
			pool.setSocketConnectTO(Integer.parseInt(s));

		s = SystemGlobals.getPreference("cache.mem.nagle");
		if (!StringUtils.isBlank(s))
			pool.setNagle(Boolean.parseBoolean(s));
		pool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);
		pool.initialize();
	}

	/**
	 * stop: (non-Javadoc).
	 *
	 * @see com.mfw.util.cache.CacheEngine#stop()
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	/**
	 * add: (non-Javadoc).
	 * @see com.mfw.util.cache.CacheEngine#add(java.io.Serializable,
	 *      java.lang.Object)
	 */
	@Override
	public void add(Serializable key, Object value) {
		if (null == value) {
			return;
		}
		long expire = EXPIRE_DEFAULT * 1000;
		this.add(key, value, expire);
	}

	/**
	 * add: (non-Javadoc).
	 * @see com.mfw.util.cache.CacheEngine#add(java.io.Serializable,
	 *      java.lang.Object, long)
	 */
	@Override
	public void add(Serializable key, Object value, long expire) {
		if (null == value) {
			return;
		}
		expire = expire * 1000;
		MemCachedClient client = new MemCachedClient();
		client.set(key.toString(), value, new Date(expire));
	}

	/**
	 * get: (non-Javadoc).
	 * @see com.mfw.util.cache.CacheEngine#get(java.io.Serializable)
	 */
	@Override
	public Object get(Serializable key) {
		MemCachedClient client = new MemCachedClient();
		return client.get(key.toString());
	}

	/**
	 * remove: (non-Javadoc).
	 * @see com.mfw.util.cache.CacheEngine#remove(java.io.Serializable)
	 */
	@Override
	public void remove(Serializable key) {
		if (!StringUtils.isBlank(key.toString())) {
			MemCachedClient client = new MemCachedClient();
			client.delete(key.toString());
		}
	}
}
