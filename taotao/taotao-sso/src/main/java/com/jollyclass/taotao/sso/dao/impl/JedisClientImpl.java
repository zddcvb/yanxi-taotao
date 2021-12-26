package com.jollyclass.taotao.sso.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jollyclass.taotao.sso.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 邹丹丹
 * @date 2017年7月25日 上午11:29:20
 * 
 */
public class JedisClientImpl implements JedisClient{
	@Autowired
	private JedisPool jedisPool;
	
	public Jedis getJedis(){
		System.out.println("getjedis");
		return jedisPool.getResource();
	}
	@Override
	public String set(String key, String value) {
		Jedis jedis = getJedis();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		Jedis jedis = getJedis();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		Jedis jedis = getJedis();
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = getJedis();
		String result = jedis.hget(hkey, key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = getJedis();
		Long result = jedis.expire(key, seconds);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = getJedis();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = getJedis();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = getJedis();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String hkey, String... fields) {
		Jedis jedis = getJedis();
		Long result = jedis.hdel(hkey, fields);
		jedis.close();
		return result;
	}
	@Override
	public String hmset(String hkey, Map<String, String> hash) {
		Jedis jedis = getJedis();
		String result = jedis.hmset(hkey, hash);
		jedis.close();
		return result;
	}

}
