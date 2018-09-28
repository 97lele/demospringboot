package com.csdn.demospringboot.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//构造链接池
public class JedisPoolWriper {
	private JedisPool jedisPool;


	public JedisPoolWriper(final JedisPoolConfig poolConfig, final String host,
						   final int port,final int timeout,final String password) {
		try {
			jedisPool = new JedisPool(poolConfig, host, port,timeout,password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public JedisPool getJedisPool() {
		return jedisPool;
	}


	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
}
