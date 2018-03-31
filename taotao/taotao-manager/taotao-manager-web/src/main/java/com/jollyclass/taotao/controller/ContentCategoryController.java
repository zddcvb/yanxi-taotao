package com.jollyclass.taotao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.TbContentCategory;
import com.jollyclass.taotao.service.ContentCategoryService;

/**
 * 分类的控制层
 * 
 * @author 邹丹丹
 * @date 2017年7月21日 下午6:51:24
 * 
 */
@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;

	/**
	 * 将所有的分类展示
	 * 
	 * @param parentId
	 *            父级id
	 * @return list集合给到页面，responsebody会将list转成json
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List getContentCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List list = new ArrayList<>();
		List<TbContentCategory> contentCategories = contentCategoryService.getContentCategoryList(parentId);
		for (TbContentCategory tbContentCategory : contentCategories) {
			if (tbContentCategory.getStatus() == 2) {
				continue;
			}
			Map map = new HashMap<>();
			map.put("id", tbContentCategory.getId());
			map.put("text", tbContentCategory.getName());
			map.put("state", tbContentCategory.getIsParent() ? "closed" : "opened");
			list.add(map);
		}
		return list;
	}

	/**
	 * 创建新的分类
	 * 
	 * @param parentId
	 *            新的分类所在的父级id
	 * @param name
	 *            新的名称
	 * @return TaoTaoResult对象
	 */
	@RequestMapping(value = "/content/category/create", method = RequestMethod.POST)
	@ResponseBody
	public TaoTaoResult createContentCategory(long parentId, String name) {
		TaoTaoResult result = contentCategoryService.createContentCategory(parentId, name);
		return result;
	}

	/**
	 * 重新命名功能
	 * 
	 * @param id
	 *            需要修改的id
	 * @param name
	 *            传递的新名称
	 * @return TaoTaoResult对象
	 */
	@RequestMapping(value = "/content/category/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateContentCategory(long id, String name) {
		contentCategoryService.updateContentCategory(id, name);
		return "content-category";
	}

	/**
	 * 删除功能,将分类的状态修改为2
	 * 
	 * @param parentId
	 *            需要删除分类的父级id
	 * @param id
	 *            需要删除分类的id
	 * @return TaoTaoResult对象
	 */
	@RequestMapping(value = "/content/category/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteContentCategory(long id) {
		contentCategoryService.deleteContentCategory(id);
		return "content-category";
	}

}
