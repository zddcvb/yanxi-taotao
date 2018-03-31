package com.jollyclass.taotao.service;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.EUDataGridResult;
import com.jollyclass.taotao.pojo.TbItem;
/**
 * 商品列表的业务层
 * @author 邹丹丹
 * @date 2017年7月17日 上午11:04:41
 * 
 */
public interface ItemService {
	/**
	 * 根据id查询商品
	 * @param itemId 商品id
	 * @return item对象
	 */
	TbItem getItemById(long itemId);
	/**
	 * list页面展示功能，并带分页
	 * @param page 当前页
	 * @param rows 每页显示的条目
	 * @return EUDataGridResult对象，封装了总条目和数据的list
	 */
	EUDataGridResult getItemList(int page,int rows);
	/**
	 * 添加新的商品
	 * @param item 页面所传的item
	 * @param desc item的描述信息
	 * @return taotaoResult对象
	 */
	TaoTaoResult createItem(TbItem item,String desc,String itemParams);
	/**
	 * 删除商品，并同步删除商品描述信息
	 * @param ids 需要删除的id号
	 * @return taotaoResult对象
	 */
	TaoTaoResult deleteItem(long[] ids);
	/**
	 * 更新商品的上架信息
	 * @param ids 需要更新的文件
	 */
	public TaoTaoResult updateItemStateUp(long[] ids);
	/**
	 * 更新商品的下架信息
	 * @param ids 需要更新的文件
	 */
	public TaoTaoResult updateItemStateDown(long[] ids);
	
}
