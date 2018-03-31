package com.jollyclass.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jollyclass.taotao.common.utils.IdUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.mapper.TbItemDescMapper;
import com.jollyclass.taotao.mapper.TbItemMapper;
import com.jollyclass.taotao.mapper.TbItemParamItemMapper;
import com.jollyclass.taotao.pojo.EUDataGridResult;
import com.jollyclass.taotao.pojo.TbItem;
import com.jollyclass.taotao.pojo.TbItemDesc;
import com.jollyclass.taotao.pojo.TbItemExample;
import com.jollyclass.taotao.pojo.TbItemExample.Criteria;
import com.jollyclass.taotao.pojo.TbItemParamItem;
import com.jollyclass.taotao.service.ItemService;

/**
 * 商品的业务层实现类
 * 
 * @author 邹丹丹
 * @date 2017年7月17日 上午11:04:41
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TbItem getItemById(long itemId) {
		// TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
		// 创建查询条件
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> items = itemMapper.selectByExample(example);
		if (items != null && items.size() > 0) {
			TbItem item = items.get(0);
			return item;
		}
		return null;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> itemList = itemMapper.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(itemList);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(itemList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/**
	 * 添加新的item至数据库中
	 * 
	 * @throws Exception
	 */
	@Override
	public TaoTaoResult createItem(TbItem item, String desc, String itemParams) {
		Date date = new Date();
		// 生成item的id
		Long itemId = IdUtils.getItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setUpdated(date);
		item.setCreated(date);
		itemMapper.insert(item);
		createItemDesc(desc, date, itemId);
		createItemParamItem(itemParams, date, itemId);
		return TaoTaoResult.ok();
	}
	/**
	 * 添加商品规格信息表
	 * @param itemParams
	 * @param date
	 * @param itemId
	 */
	private void createItemParamItem(String itemParams, Date date, Long itemId) {
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParams);
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		itemParamItemMapper.insert(itemParamItem);
	}
	/**
	 * 添加新的商品描述表
	 * @param desc
	 * @param date
	 * @param itemId
	 * @return
	 */
	private TaoTaoResult createItemDesc(String desc, Date date, Long itemId) {
		// 保存desc信息
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDescMapper.insert(itemDesc);
		return TaoTaoResult.ok();
	}
	/**
	 * 刪除商品
	 * @param ids 需要刪除的商品id
	 * @return TaoTaoResult對象
	 */
	@Override
	public TaoTaoResult deleteItem(long[] ids) {
		if (ids.length > 0) {
			for (long id : ids) {
				// 更新表中数据，将状态修改未3，删除状态
				TbItemExample example = new TbItemExample();
				example.createCriteria().andIdEqualTo(id);
				List<TbItem> list = itemMapper.selectByExample(example);
				if (list!=null&&list.size()>0) {
					TbItem tbItem = list.get(0);
					tbItem.setStatus((byte) 3);
					itemMapper.updateByPrimaryKey(tbItem);
				}
			}
			return TaoTaoResult.ok();
		}
		return null;
	}
	/**
	 * 上架商品
	 * @param ids 需要上架的商品id
	 * @return TaoTaoResult對象
	 */
	@Override
	public TaoTaoResult updateItemStateUp(long[] ids) {
		if (ids.length>0) {
			for (long id : ids) {
				TbItemExample example=new TbItemExample();
				example.createCriteria().andIdEqualTo(id);
				List<TbItem> list = itemMapper.selectByExample(example);
				if (list!=null&&list.size()>0) {
					TbItem tbItem = list.get(0);
					tbItem.setStatus((byte) 1);
					itemMapper.updateByPrimaryKey(tbItem);
				}
			}
			return TaoTaoResult.ok();
		}
		return null;				
	}
	/**
	 * 下架商品
	 * @param ids 需要下架的商品id
	 * @return TaoTaoResult對象
	 */
	@Override
	public TaoTaoResult updateItemStateDown(long[] ids) {
		if (ids.length>0) {
			for (long id : ids) {
				TbItemExample example=new TbItemExample();
				example.createCriteria().andIdEqualTo(id);
				List<TbItem> list = itemMapper.selectByExample(example);
				if (list!=null&&list.size()>0) {
					TbItem tbItem = list.get(0);
					tbItem.setStatus((byte) 2);
					itemMapper.updateByPrimaryKey(tbItem);
				}
			}
			return TaoTaoResult.ok();
		}
		return null;
	}

}
