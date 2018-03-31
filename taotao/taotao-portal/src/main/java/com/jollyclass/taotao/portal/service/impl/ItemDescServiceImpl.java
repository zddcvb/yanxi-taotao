package com.jollyclass.taotao.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.HttpClientUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.TbItem;
import com.jollyclass.taotao.pojo.TbItemDesc;
import com.jollyclass.taotao.portal.service.ItemDescService;

/**
 * @author 邹丹丹
 * @date 2017年7月27日 下午5:54:56
 * 
 */
@Service
public class ItemDescServiceImpl implements ItemDescService{
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_BASE_DESC}")
	private String ITEM_BASE_DESC;
	@Override
	public TbItemDesc getItemDescByID(long itemId) {
		try {
			String json = HttpClientUtils.doGet(REST_BASE_URL+ITEM_BASE_DESC+itemId);
			if (!StringUtils.isBlank(json)) {
				TaoTaoResult taoResult = TaoTaoResult.formatToPojo(json, TbItemDesc.class);
				if (taoResult.getStatus()==200) {
					TbItemDesc itemDesc=(TbItemDesc) taoResult.getData();
					return itemDesc;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
