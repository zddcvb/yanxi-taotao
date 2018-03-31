package com.jollyclass.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.mapper.TbItemParamItemMapper;
import com.jollyclass.taotao.pojo.TbItemDesc;
import com.jollyclass.taotao.pojo.TbItemParamItem;
import com.jollyclass.taotao.pojo.TbItemParamItemExample;
import com.jollyclass.taotao.rest.dao.JedisClient;
import com.jollyclass.taotao.rest.service.ItemParamItemService;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午4:37:30
 * 
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_EXPIRE_TIME}")
	private int REDIS_EXPIRE_TIME;

	@Override
	public TaoTaoResult getItemParamInfo(long itemId) {
		// 获得缓存
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
			if (StringUtils.isBlank(json)) {
				TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return TaoTaoResult.ok(itemParamItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 没有缓存，数据库中取
		TbItemParamItemExample example = new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			TbItemParamItem tbItemParamItem = list.get(0);
			// 缓存数据
			try {
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(tbItemParamItem));
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_EXPIRE_TIME);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return TaoTaoResult.ok(tbItemParamItem);
		}
		return TaoTaoResult.ok();
	}

}
