package com.jollyclass.taotao.order.dao;

import java.util.Map;

/**
 * @author 邹丹丹
 * @date 2017年7月25日 上午11:23:46
 * 
 */
public interface JedisClient {
	public String set(String key,String value);
	public String get(String key);
	public Long hset(String hkey,String field,String value);
	public String hmset(String hkey,Map<String, String> hash);
	public String hget(String hkey,String field);
	public Long expire(String key,int seconds);
	public Long ttl(String key);
	public Long incr(String key);
	public Long del(String key);
	public Long hdel(String hkey,String... field);
}
