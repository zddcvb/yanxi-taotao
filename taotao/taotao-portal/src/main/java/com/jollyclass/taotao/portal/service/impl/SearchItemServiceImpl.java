package com.jollyclass.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.HttpClientUtils;
import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.portal.service.SearchItemService;
import com.jollyclass.taotao.protal.pojo.SearchResult;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午5:38:32
 * 
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SearchResult search(String query, int page) {
		System.out.println("query:"+query);
		Map<String, String> params = new HashMap<String, String>();
		params.put("q", query);
		params.put("page", page+"");
		params.put("rows", 20+"");
		String data = null;
		SearchResult searchResult =null;
		try {
			//获得json数据
			data = HttpClientUtils.doGet(SEARCH_BASE_URL, params);
			System.out.println(data);
			//将json数据转换成pojo对象,由于doget传过来的是taotaoResult对象的json，所以需采用taotaoresult的工具类来转换
			TaoTaoResult taoTaoResult = TaoTaoResult.formatToPojo(data,SearchResult.class);
			if (taoTaoResult.getStatus()==200) {
				 searchResult=(SearchResult) taoTaoResult.getData();
				 return searchResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
