package com.jollyclass.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.EUDataGridResult;
import com.jollyclass.taotao.pojo.TbItemParam;
import com.jollyclass.taotao.service.ItemParamService;

/**
 * 规格参数控制层
 * 
 * @author 邹丹丹
 * @date 2017年7月20日 下午2:03:39
 * 
 */
@Controller
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;

	/**
	 * 展示所有的规格参数
	 * 
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示的数据
	 * @return EUDataGridResult对象
	 */
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EUDataGridResult getItemParamList(Integer page, Integer rows) {
		System.out.println("start");
		EUDataGridResult result = itemParamService.getParamItemList(page, rows);
		System.out.println("result:"+result.getRows().size());
		return result;
	}
	/**
	 * 删除数据
	 * @param ids 页面传递出来的数据
	 * @return TaoTaoResult对象，json格式
	 */
	@RequestMapping(value="/item/param/delete", method = RequestMethod.POST)
	@ResponseBody
	public TaoTaoResult deleteItemParam(long[] ids) {
		TaoTaoResult result = itemParamService.deleteItemParam(ids);
		return result;
	}
	/**
	 * 在tb_item_param中查询itemcatid对应的数据是否存在
	 * @param itemCatId
	 * @return TaoTaoResult对象，如果已经存在则会带有数据，如果不存在则不带数据
	 */
	@RequestMapping("/item/param/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaoTaoResult queryByItemCatId(@PathVariable Long itemCatId){
		System.out.println("itemCatId:"+itemCatId);
		TaoTaoResult result=itemParamService.queryByItemCatId(itemCatId);
		TbItemParam itemParam=(TbItemParam)result.getData();
		System.out.println(itemParam.getItemCatId());
		return result;
	}
	/**
	 * 添加新的itemParam
	 * @param itemParam 页面所传的itemParame
	 * @param itemCatId 传递的参数
	 * @return TaoTaoResult对象
	 */
	@RequestMapping(value="/item/param/save/{itemCatId}",method=RequestMethod.POST)
	@ResponseBody
	public TaoTaoResult createItemParam(TbItemParam itemParam,@PathVariable Long itemCatId){
		TaoTaoResult result = itemParamService.createItemParam(itemParam, itemCatId);
		return result;
	}
}
