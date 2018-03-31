package com.jollyclass.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.EUDataGridResult;
import com.jollyclass.taotao.pojo.TbItem;
import com.jollyclass.taotao.service.ItemService;

/**
 * 商品的控制层
 * 
 * @author 邹丹丹
 * @date 2017年7月17日 上午11:04:41
 * 
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	/**
	 * 根据id获得商品
	 * 
	 * @param itemId
	 *            itemId
	 * @return Item的json数据
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem item = itemService.getItemById(itemId);
		return item;
	}

	/**
	 * 列表功能，并带分页
	 * 
	 * @param page
	 *            当前的页数
	 * @param rows
	 *            当前显示的数据条目
	 * @return EUDataGridResult对象
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	/**
	 * 新增一个item数据
	 * 
	 * @param item
	 *            item对象，jsp页面中的字段必须与item对象一致
	 * @param desc
	 *            item的描述信息，保存到item_desc中
	 * @return 返回一個taotaoResult对象
	 */
	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	public TaoTaoResult createItem(TbItem item, String desc, String itemParams) {
		TaoTaoResult taoTaoResult = itemService.createItem(item, desc, itemParams);
		return taoTaoResult;
	}

	/**
	 * 删除item信息，同步删除itemDesc中的信息
	 * 
	 * @param ids
	 *            选中的数据
	 * @return 返回一個taotaoResult对象
	 */
	@RequestMapping(value = "/rest/item/delete", method = RequestMethod.POST)
	@ResponseBody
	public TaoTaoResult deleteItem(long[] ids) {
		TaoTaoResult result = itemService.deleteItem(ids);
		return result;
	}

	/**
	 * 上架功能
	 * 
	 * @param ids
	 *            需要上架的id
	 * @return TaoTaoResult对象
	 */
	@RequestMapping(value = "/rest/item/reshelf", method = RequestMethod.POST)
	@ResponseBody
	public TaoTaoResult updateItemStateUp(long[] ids) {
		TaoTaoResult result = itemService.updateItemStateUp(ids);
		return result;
	}

	/**
	 * 下架功能
	 * 
	 * @param ids
	 *            需要下架的id
	 * @return TaoTaoResult对象
	 */
	@RequestMapping(value = "/rest/item/instock", method = RequestMethod.POST)
	@ResponseBody
	public TaoTaoResult updateItemStateDown(long[] ids) {
		TaoTaoResult result = itemService.updateItemStateDown(ids);
		return result;
	}

}
