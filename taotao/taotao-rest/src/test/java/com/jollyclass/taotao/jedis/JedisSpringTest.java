package com.jollyclass.taotao.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * @author 邹丹丹
 * @date 2017年7月25日 上午11:18:17
 * 
 */
public class JedisSpringTest {
	/**
	 * 单机版jedis
	 */
	@SuppressWarnings("resource")
	@Test
	public void testJedisSingle() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-jedis.xml");
		JedisPool jedisPool = (JedisPool) context.getBean("jedisPool");
		Jedis jedis = jedisPool.getResource();
		jedis.set("age", "20");
		String age = jedis.get("age");
		System.out.println(age);
		jedis.close();
		jedisPool.close();
	}
	/**
	 * 集群版jedis
	 */
	@SuppressWarnings("resource")
	@Test
	public void testJedisCluster(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-jedis.xml");
		JedisCluster cluster=(JedisCluster) context.getBean("cluster");
		cluster.set("name", "jack");
		String name = cluster.get("name");
		System.out.println(name);
		cluster.close();
	}
}
