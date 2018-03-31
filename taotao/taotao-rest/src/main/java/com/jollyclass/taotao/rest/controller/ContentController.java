package com.jollyclass.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.common.utils.ExceptionUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.TbContent;
import com.jollyclass.taotao.rest.service.ContentService;

/**
 * 内容的控制层
 * @author 邹丹丹
 * @date 2017年7月22日 下午2:44:24
 * 
 */
@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	/**
	 * 根据id查询内容，并返回list数据以及相关的状态
	 * @param categoryId 查询的分类id
	 * @return TaoTaoResult对象，封装了状态信息，数据信息，以及错误信息
	 */
	@RequestMapping("/content/list/{categoryId}")
	@ResponseBody
	public TaoTaoResult getContentList(@PathVariable long categoryId) {
		try {
			List<TbContent> list = contentService.getContentList(categoryId);
			return TaoTaoResult.ok(list);
		} catch (Exception e) {
			return TaoTaoResult.build(404, ExceptionUtils.getStackTrace(e));
		}
		
	}
}
