package com.jollyclass.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.mapper.TbContentMapper;
import com.jollyclass.taotao.pojo.TbContent;
import com.jollyclass.taotao.pojo.TbContentExample;
import com.jollyclass.taotao.rest.dao.JedisClient;
import com.jollyclass.taotao.rest.service.ContentService;

/**
 * @author 邹丹丹
 * @date 2017年7月22日 下午2:42:20
 * 
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	@Override
	public List<TbContent> getContentList(long categoryId) {
		//需要将查询的内容放进缓存中，已categoryId为key，value为查询的数据
		//第一步从缓存中查询数据
		String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, categoryId+"");
		try {
			if (!StringUtils.isBlank(result)) {
				List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//第二步：缓存中没有数据，则从数据库中查询，并保存到缓存中
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		try {
			//将list转换成字符串
			String data = JsonUtils.objectToJson(list);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, categoryId+"", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
