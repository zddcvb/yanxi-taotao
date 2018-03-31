package com.jollyclass.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.jollyclass.taotao.common.utils.ExceptionUtils;
import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.mapper.TbItemMapper;
import com.jollyclass.taotao.pojo.TbItem;
import com.jollyclass.taotao.pojo.TbItemExample;
import com.jollyclass.taotao.rest.dao.JedisClient;
import com.jollyclass.taotao.rest.service.ItemService;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午4:26:11
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_EXPIRE_TIME}")
	private int REDIS_EXPIRE_TIME;
	@Override
	public TaoTaoResult getItemBaseInfo(Long itemId) {
		//从缓存中取数据:定义：REDIS_ITEM_KEY:商品id:base=json
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":base");
			if (StringUtils.isBlank(json)) {
				TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
				return TaoTaoResult.ok(tbItem);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		//缓存中没有，从数据库中取数据
		TbItemExample example = new TbItemExample();
		example.createCriteria().andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem tbItem = list.get(0);
			//存入缓存
			try {
				jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":base", JsonUtils.objectToJson(tbItem));
				//设置过期时间
				jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":base", REDIS_EXPIRE_TIME);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return TaoTaoResult.ok(tbItem);
		}
		return TaoTaoResult.ok();
	}

}
