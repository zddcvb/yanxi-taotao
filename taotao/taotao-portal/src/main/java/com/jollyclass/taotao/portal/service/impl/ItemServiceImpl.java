package com.jollyclass.taotao.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.HttpClientUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.TbItem;
import com.jollyclass.taotao.portal.service.ItemService;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午5:44:34
 * 
 */
@Service
public class ItemServiceImpl implements ItemService{
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_BASE_INFO}")
	private String ITEM_BASE_INFO;
	@Override
	public TbItem getItem(long itemId) {
		try {
			String json = HttpClientUtils.doGet(REST_BASE_URL+ITEM_BASE_INFO+itemId);
			if (!StringUtils.isBlank(json)) {
				TaoTaoResult taoResult = TaoTaoResult.formatToPojo(json, TbItem.class);
				if (taoResult.getStatus()==200) {
					TbItem item=(TbItem) taoResult.getData();
					return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
