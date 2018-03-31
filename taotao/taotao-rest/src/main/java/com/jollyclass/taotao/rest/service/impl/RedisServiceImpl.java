package com.jollyclass.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.ExceptionUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.rest.dao.JedisClient;
import com.jollyclass.taotao.rest.service.RedisService;

/**
 * redis的业务实现层
 * @author 邹丹丹
 * @date 2017年7月25日 下午1:30:34
 * 
 */
@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	@Override
	public TaoTaoResult syncContent(long categoryId) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, categoryId+"");
		} catch (Exception e) {
			e.printStackTrace();
			return TaoTaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return TaoTaoResult.ok();
	}

}
