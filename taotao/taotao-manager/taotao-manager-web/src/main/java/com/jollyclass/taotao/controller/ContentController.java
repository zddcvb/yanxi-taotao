package com.jollyclass.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.EUDataGridResult;
import com.jollyclass.taotao.pojo.TbContent;
import com.jollyclass.taotao.service.ContentService;

/**
 * @author 邹丹丹
 * @date 2017年7月22日 上午11:29:41
 * 
 */
@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	/**
	 * 展示所有的内容，并分页显示
	 * @param page 当前页
	 * @param rows 每页显示的数据
	 * @return EUDataGridResult对象
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EUDataGridResult getContentList(int page, int rows) {
		EUDataGridResult result = contentService.getContentList(page, rows);
		return result;
	}
	/**
	 * 删除功能
	 * @param ids 需要删除的id数据
	 * @return TaoTaoResult对象
	 */
	@RequestMapping("/content/delete")
	@ResponseBody
	public TaoTaoResult deleteContent(long[] ids){
		TaoTaoResult result=contentService.deleteContent(ids);
		return result;
	}
	/**
	 * 添加新的content
	 * @param tbContent 需要添加的content
	 * @return TaoTaoResult對象
	 */
	@RequestMapping(value="/content/save",method=RequestMethod.POST)
	@ResponseBody
	public TaoTaoResult createContent(TbContent tbContent){
		TaoTaoResult result=contentService.createContent(tbContent);
		return result;
	}
	/**
	 * 编辑content
	 * @param tbContent 需要添加的content
	 * @return TaoTaoResult對象
	 */
	@RequestMapping(value="/rest/content/edit",method=RequestMethod.POST)
	@ResponseBody
	public TaoTaoResult updateContent(TbContent tbContent){
		TaoTaoResult result=contentService.updateContent(tbContent);
		return result;
	}
}
