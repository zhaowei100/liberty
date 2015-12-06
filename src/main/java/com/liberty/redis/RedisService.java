package com.liberty.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisService {

	private final static int maxTotal = 10;
	private final static int maxIdle = 5;
	private final static int maxWait = 3000;
	private final static boolean testOnBorrow = true;
	private final static boolean testOnReturn = true;
	private final static String host = "localhost";
	private static JedisPool jedisPool = null;

	public static Jedis getJedis() {

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWait);
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		jedisPoolConfig.setTestOnReturn(testOnReturn);
		jedisPool = new JedisPool(jedisPoolConfig, host);

		return jedisPool.getResource();
	}

	public void set(String key, long num) {
		Jedis jedis = getJedis();
		jedis.set(key, Long.toString(num));
		jedis.close();
	}
	
	public long inc(String key){
		Jedis jedis = getJedis();
		long num = jedis.incr(key);
		jedis.close();
		return num;
	}

	public static void main(String[] args) {
		RedisService redisService = new RedisService();
		String key = "test";
		redisService.set(key, 1000);
		System.out.println(redisService.inc(key));
	}
}
