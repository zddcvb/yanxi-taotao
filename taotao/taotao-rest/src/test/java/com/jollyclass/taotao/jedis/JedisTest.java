package com.jollyclass.taotao.jedis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 邹丹丹
 * @date 2017年7月25日 上午10:33:17
 * 
 */
public class JedisTest {
	String url="192.168.195.128";
	/**
	 * 单机版Jedis，不用连接池
	 */
	@Test
	public void testJedisSingle() {
		Jedis jedis = new Jedis(url, 6379);
		jedis.set("username01", "jack01");
		String username = jedis.get("username01");
		System.out.println("username:" + username);
		jedis.close();
	}

	/**
	 * 单机版jedis 带连接池
	 */
	@Test
	public void testJedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(10);
		config.setMaxTotal(30);
		JedisPool pool = new JedisPool(config, url, 6379);
		Jedis jedis = pool.getResource();
		jedis.set("username02", "jack02");
		String username = jedis.get("username02");
		System.out.println("username:" + username);
		jedis.close();
		pool.close();
	}

	/**
	 * 集群版jedis
	 */
	@Test
	public void testJedisCluste() {
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort(url, 7001));
		nodes.add(new HostAndPort(url, 7002));
		nodes.add(new HostAndPort(url, 7003));
		nodes.add(new HostAndPort(url, 7004));
		nodes.add(new HostAndPort(url, 7005));
		nodes.add(new HostAndPort(url, 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("username", "jack");
		cluster.hset("mhash", "name", "mary");
		cluster.lpush("mlist", "name", "jack");
		cluster.sadd("mset", "name", "lucy");
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("jack", 500D);
		map.put("jack01", 400D);
		map.put("jack02", 600D);
		cluster.zadd("msort", map);

		String username = cluster.get("username");
		String husername = cluster.hget("mhash", "username");
		List<String> mList = cluster.lrange("mlist", 0L, 6L);
		Set<String> mSet = cluster.smembers("mset");
		Set<String> msort = cluster.zrange("msort", 0, 6);
		System.out.println(username + ":" + husername + ":" + mList + ":" + mSet + ":" + msort);
		cluster.close();
	}
}
