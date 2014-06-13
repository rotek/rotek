package com.cta.platform.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author chenwenpeng
 * 
 */
public class RedisEngine {

	private static ShardedJedisPool redisPool;

	private String redisIps = "127.0.0.1:6379";
	private Integer redisMaxActive = 2000;
	private Integer redisMaxWait = 10000;
	private Boolean redisTestOnBorrow = true;
	private Boolean redisTestOnReturn = true;
	private Integer redisMaxIdle = 50;
	private Integer redisMinIdle = 0;
	private Boolean redisTestWhileIdle = true;
	private Integer redisNumTestsPerEvictionRun = 10;
	private Integer redisTimeBetweenEvictionRunsMillis = 30000;

	// 初始化
	public void init() {

		// 初始化write redis pool
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxActive(redisMaxActive);
		poolConfig.setMaxWait(redisMaxWait);
		poolConfig.setTestOnBorrow(redisTestOnBorrow);
		poolConfig.setTestOnReturn(redisTestOnReturn);
		poolConfig.setMaxIdle(redisMaxIdle);
		poolConfig.setMinIdle(redisMinIdle);
		poolConfig.setTestWhileIdle(redisTestWhileIdle);
		poolConfig.setNumTestsPerEvictionRun(redisNumTestsPerEvictionRun);
		poolConfig.setTimeBetweenEvictionRunsMillis(redisTimeBetweenEvictionRunsMillis);

		if (StringUtils.isNotEmpty(this.redisIps)) {// 配置的格式为ip:端口:password
												// ,多条配置用逗号分隔
			String[] strs = redisIps.split(",");
			List<JedisShardInfo> writeShards = new ArrayList<JedisShardInfo>(
					strs.length);
			String[] ss = null;
			JedisShardInfo shardInfo = null;
			String ip = null;
			Integer port = null;
			for (String str : strs) {
				ss = str.split(":");
				ip = ss[0];
				if (ss.length > 1) {
					port = Integer.valueOf(ss[1]);
				} else {
					port = 6379;
				}
				shardInfo = new JedisShardInfo(ip, port);
				if (ss.length > 2)
					shardInfo.setPassword(ss[2]);
				writeShards.add(shardInfo);
			}

			redisPool = new ShardedJedisPool(poolConfig, writeShards);
		}
	}

	public void destroy() {

		if (redisPool != null) {
			redisPool.destroy();
		}
	}

	public ShardedJedis getJedis() {
		return redisPool.getResource();
	}

	public void returnWriteResource(ShardedJedis jedis) {
		if (jedis != null) {
			redisPool.returnResource(jedis);
		}
	}

	public void returnBrokenResource(ShardedJedis jedis) {
		if (jedis != null) {
			redisPool.returnBrokenResource(jedis);
		}
	}

	public static ShardedJedisPool getRedisPool() {
		return redisPool;
	}

	public static void setRedisPool(ShardedJedisPool redisPool) {
		RedisEngine.redisPool = redisPool;
	}

	public String getRedisIps() {
		
		return redisIps;
	}

	public void setRedisIps(String redisIps) {
		
		this.redisIps = redisIps;
	}

	public Integer getRedisMaxActive() {
		return redisMaxActive;
	}

	public void setRedisMaxActive(Integer redisMaxActive) {
		this.redisMaxActive = redisMaxActive;
	}

	public Integer getRedisMaxWait() {
		return redisMaxWait;
	}

	public void setRedisMaxWait(Integer redisMaxWait) {
		this.redisMaxWait = redisMaxWait;
	}

	public Boolean getRedisTestOnBorrow() {
		return redisTestOnBorrow;
	}

	public void setRedisTestOnBorrow(Boolean redisTestOnBorrow) {
		this.redisTestOnBorrow = redisTestOnBorrow;
	}

	public Boolean getRedisTestOnReturn() {
		return redisTestOnReturn;
	}

	public void setRedisTestOnReturn(Boolean redisTestOnReturn) {
		this.redisTestOnReturn = redisTestOnReturn;
	}

	public Integer getRedisMaxIdle() {
		return redisMaxIdle;
	}

	public void setRedisMaxIdle(Integer redisMaxIdle) {
		this.redisMaxIdle = redisMaxIdle;
	}

	public Integer getRedisMinIdle() {
		return redisMinIdle;
	}

	public void setRedisMinIdle(Integer redisMinIdle) {
		this.redisMinIdle = redisMinIdle;
	}

	public Boolean getRedisTestWhileIdle() {
		return redisTestWhileIdle;
	}

	public void setRedisTestWhileIdle(Boolean redisTestWhileIdle) {
		this.redisTestWhileIdle = redisTestWhileIdle;
	}

	public Integer getRedisNumTestsPerEvictionRun() {
		return redisNumTestsPerEvictionRun;
	}

	public void setRedisNumTestsPerEvictionRun(
			Integer redisNumTestsPerEvictionRun) {
		this.redisNumTestsPerEvictionRun = redisNumTestsPerEvictionRun;
	}

	public Integer getRedisTimeBetweenEvictionRunsMillis() {
		return redisTimeBetweenEvictionRunsMillis;
	}

	public void setRedisTimeBetweenEvictionRunsMillis(
			Integer redisTimeBetweenEvictionRunsMillis) {
		this.redisTimeBetweenEvictionRunsMillis = redisTimeBetweenEvictionRunsMillis;
	}

}
