package com.jollyclass.taotao.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.mapper.TbItemParamMapper;
import com.jollyclass.taotao.pojo.EUDataGridResult;
import com.jollyclass.taotao.pojo.TbItemParam;
import com.jollyclass.taotao.pojo.TbItemParamExample;
import com.jollyclass.taotao.service.ItemParamService;

/**
 * 商品规格参数的业务实现层
 * 
 * @author 邹丹丹
 * @date 2017年7月20日 下午1:57:47
 * 
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public EUDataGridResult getParamItemList(int page, int rows) {
		EUDataGridResult result = new EUDataGridResult();
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = itemParamMapper.selectAll();
		System.out.println(list.size());
		result.setRows(list);
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		System.out.println("total:" + pageInfo.getTotal());
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaoTaoResult deleteItemParam(long[] ids) {
		if (ids.length > 0) {
			for (long id : ids) {
				TbItemParamExample example = new TbItemParamExample();
				example.createCriteria().andIdEqualTo(id);
				itemParamMapper.deleteByExample(example);
				return TaoTaoResult.ok();
			}
		}
		return null;
	}

	@Override
	public TaoTaoResult queryByItemCatId(Long itemCatId) {
		TbItemParamExample example = new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(itemCatId);
		//由于数据是一串大文本，所以需要采用selectByExampleWithBLOBs
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			return TaoTaoResult.ok(list.get(0));
		}
		return TaoTaoResult.ok();
	}

	@Override
	public TaoTaoResult createItemParam(TbItemParam itemParam, long itemCatId) {
		Date date = new Date();
		itemParam.setItemCatId(itemCatId);
		itemParam.setCreated(date);
		itemParam.setUpdated(date);
		itemParamMapper.insert(itemParam);
		return TaoTaoResult.ok();
	}

}
