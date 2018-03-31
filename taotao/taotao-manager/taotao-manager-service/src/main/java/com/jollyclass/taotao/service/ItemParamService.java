package com.jollyclass.taotao.service;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.EUDataGridResult;
import com.jollyclass.taotao.pojo.TbItemParam;

/**
 * @author 邹丹丹
 * @date 2017年7月20日 下午1:54:07
 * 
 */
public interface ItemParamService {
	/**
	 * 展示规格参数，并分页
	 * 
	 * @param page
	 *            当前页
	 * @param rows
	 *            一页展示的数据
	 * @return EUDataGridResult对象
	 */
	public EUDataGridResult getParamItemList(int page, int rows);

	/**
	 * 根据itemId删除数据
	 * 
	 * @param ids
	 *            一组需要删除的数据的id
	 * @return TaoTaoResult对象
	 */
	public TaoTaoResult deleteItemParam(long[] ids);
	/**
	 * 根据itemCatId查询itemParam
	 * @param itemCatId
	 * @return TaoTaoResult对象
	 */
	public TaoTaoResult queryByItemCatId(Long itemCatId);
	/**
	 * 新增item规格参数
	 * @param itemParam 
	 * @return TaoTaoResult对象
	 */
	public TaoTaoResult createItemParam(TbItemParam itemParam,long itemCatId);
}
