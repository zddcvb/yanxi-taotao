package com.jollyclass.taotao.order.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jollyclass.taotao.order.dao.JedisClient;

import redis.clients.jedis.JedisCluster;

/**
 * @author 邹丹丹
 * @date 2017年7月25日 上午11:42:59
 * 
 */
public class JedisClusterImpl implements JedisClient {
	@Autowired
	private JedisCluster cluster;

	@Override
	public String set(String key, String value) {

		return cluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return cluster.get(key);
	}

	@Override
	public Long hset(String hkey, String field, String value) {
		return cluster.hset(hkey, field, value);
	}

	@Override
	public String hmset(String hkey, Map<String, String> hash) {
		return cluster.hmset(hkey, hash);
	}

	@Override
	public String hget(String hkey, String field) {
		return cluster.hget(hkey, field);
	}

	@Override
	public Long expire(String key, int seconds) {
		return cluster.expire(key, seconds);
	}

	@Override
	public Long ttl(String key) {
		return cluster.ttl(key);
	}

	@Override
	public Long incr(String key) {
		return cluster.incr(key);
	}

	@Override
	public Long del(String key) {
		return cluster.del(key);
	}

	@Override
	public Long hdel(String hkey, String... field) {
		return cluster.hdel(hkey, field);
	}

}
