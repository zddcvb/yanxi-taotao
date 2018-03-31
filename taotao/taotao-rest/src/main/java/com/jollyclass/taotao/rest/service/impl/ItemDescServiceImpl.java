package com.jollyclass.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.mapper.TbItemDescMapper;
import com.jollyclass.taotao.pojo.TbItemDesc;
import com.jollyclass.taotao.pojo.TbItemDescExample;
import com.jollyclass.taotao.rest.dao.JedisClient;
import com.jollyclass.taotao.rest.service.ItemDescService;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午4:31:50
 * 
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_EXPIRE_TIME}")
	private int REDIS_EXPIRE_TIME;
	@Override
	public TaoTaoResult getItemDescInfo(Long itemId) {
		System.out.println("rest itemId:"+itemId);
		//获得缓存
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":desc");
			if (!StringUtils.isBlank(json)) {
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return TaoTaoResult.ok(itemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//没有缓存，数据库中取
		TbItemDescExample example = new TbItemDescExample();
		example.createCriteria().andItemIdEqualTo(itemId);
		List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			TbItemDesc tbItemDesc = list.get(0);
			//缓存数据
			try{
				jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":desc", JsonUtils.objectToJson(tbItemDesc));
				jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":desc", REDIS_EXPIRE_TIME);
			}catch(Exception e){
				e.printStackTrace();
			}
			return TaoTaoResult.ok(tbItemDesc);
		}
	
		return TaoTaoResult.ok();
	}

}
