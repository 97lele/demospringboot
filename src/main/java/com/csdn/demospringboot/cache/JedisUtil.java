package com.csdn.demospringboot.cache;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

public class JedisUtil {
	/** 操作Key的方法 */
	public Keys KEYS;
	/** 对存储结构为String类型的操作 */
	public Strings STRINGS;

	/** Redis连接池对象 */
	private JedisPool jedisPool;

	/**
	 * 获取redis连接池
	 *
	 * @return
	 */
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	/**
	 * 设置redis连接池
	 *
	 * @return
	 */
	public void setJedisPool(JedisPoolWriper jedisPoolWriper) {
		this.jedisPool = jedisPoolWriper.getJedisPool();
	}

	/**
	 * 从jedis连接池中获取获取jedis对象
	 *
	 * @return
	 */
	public Jedis getJedis() {
		Jedis jedis=jedisPool.getResource();

		return jedis;
	}

	// *******************************************Keys*******************************************//
	public class Keys {

		public void expire(String key, int seconds) {
			if (seconds <= 0) {
				return;
			}
			Jedis jedis = getJedis();
			jedis.expire(key, seconds);
			jedis.close();
		}

		/**
		 * 清空所有key
		 */
		public String flushAll() {
			Jedis jedis = getJedis();
			String stata = jedis.flushAll();
			jedis.close();
			return stata;
		}


		public long del(String... keys) {
			Jedis jedis = getJedis();
			long count = jedis.del(keys);
			jedis.close();
			return count;
		}

		public long pttl(String key){
			Jedis sjedis=getJedis();
			long time=sjedis.pttl(key);
			sjedis.close();
			return time;
		}


		public boolean exists(String key) {
			// ShardedJedis sjedis = getShardedJedis();
			Jedis sjedis = getJedis();
			boolean exis = sjedis.exists(key);
			sjedis.close();
			return exis;
		}

		/**
		 * 查找所有匹配给定的模式的键
		 *

		 *            key的表达式,*表示多个，？表示一个
		 */
		public Set<String> keys(String pattern) {
			Jedis jedis = getJedis();
			Set<String> set = jedis.keys(pattern);
			jedis.close();
			return set;
		}
	}

	// *******************************************Strings*******************************************//
	public class Strings {
		/**
		 * 根据key获取记录
		 *

		 * @return 值
		 */
		public String get(String key) {
			// ShardedJedis sjedis = getShardedJedis();
			Jedis sjedis = getJedis();
			String value = sjedis.get(key);
			sjedis.close();
			return value;
		}

		/**
		 * 添加记录,如果记录已存在将覆盖原有的value
		 *

		 * @return 状态码
		 */
		public String set(String key, String value) {
			return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
		}

		/**
		 * 添加记录,如果记录已存在将覆盖原有的value

		 *            value
		 * @return 状态码
		 */
		public String set(byte[] key, byte[] value) {
			Jedis jedis = getJedis();
			String status = jedis.set(key, value);
			jedis.close();
			return status;
		}

	}

}