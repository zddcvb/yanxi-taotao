package com.jollyclass.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.jollyclass.taotao.common.utils.HttpClientUtils;
import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.TbContent;
import com.jollyclass.taotao.portal.service.ContentService;

/**
 * @author 邹丹丹
 * @date 2017年7月22日 下午4:42:16
 * 
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String getContentList() {
		//System.out.println(REST_BASE_URL + REST_INDEX_AD_URL);
		// 从rest中获取json字符串
		String json = HttpClientUtils.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		try {
			// 将字符串转换成taotaoresule对象
			TaoTaoResult result = TaoTaoResult.formatToList(json, TbContent.class);
			List<TbContent> tbContents = (List<TbContent>) result.getData();
			List<Map> list = new ArrayList<Map>();
			for (TbContent tbContent : tbContents) {
				Map map = new HashMap<>();
				map.put("srcB", tbContent.getPic());
				map.put("height", 240);
				map.put("alt", tbContent.getSubTitle());
				map.put("width", 670);
				map.put("src", tbContent.getPic2());
				map.put("widthB", 550);
				map.put("href", tbContent.getUrl());
				map.put("heightB", 240);
				list.add(map);
			}
			//System.out.println(list);
			String objectToJson = JsonUtils.objectToJson(list);
			//System.out.println(objectToJson);
			return objectToJson;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

}
