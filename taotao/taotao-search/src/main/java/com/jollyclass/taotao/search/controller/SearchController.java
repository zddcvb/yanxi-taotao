package com.jollyclass.taotao.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jollyclass.taotao.common.utils.ExceptionUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.search.pojo.SearchResult;
import com.jollyclass.taotao.search.service.SearchService;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午3:27:44
 * 
 */
@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	/**
	 * 查询服务
	 * @param query 查询条件
	 * @param page 当前页
	 * @param rows 显示多少条数据
	 * @return TaoTaoResult对象，400，表示查询调件未空
	 */
	@RequestMapping("/query")
	@ResponseBody
	public TaoTaoResult  search(@RequestParam("q") String query, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue="20") int rows) {
		System.out.println("服务的query："+query);
		//判断是否为空
		if (StringUtils.isBlank(query)) {
			return TaoTaoResult.build(400, "查询条件不能为空");
		}
		//查询数据,封裝到Taotaoresult中
		SearchResult result=null;
		try {
			//解决乱码问题
			query=new String(query.getBytes("iso8859-1"), "utf-8");
			System.out.println("query:"+query);
			 result = searchService.search(query, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return TaoTaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return TaoTaoResult.ok(result);
	}
}
