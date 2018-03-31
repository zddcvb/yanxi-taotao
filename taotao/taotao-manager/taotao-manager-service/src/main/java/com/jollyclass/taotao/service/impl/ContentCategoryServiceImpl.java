package com.jollyclass.taotao.service.impl;

import java.awt.event.ComponentEvent;
import java.util.Date;
import java.util.List;

import org.apache.log4j.spi.LoggingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.mapper.TbContentCategoryMapper;
import com.jollyclass.taotao.pojo.TbContentCategory;
import com.jollyclass.taotao.pojo.TbContentCategoryExample;
import com.jollyclass.taotao.service.ContentCategoryService;

/**
 * 分类信息的业务实现层
 * 
 * @author 邹丹丹
 * @date 2017年7月21日 下午7:00:51
 * 
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<TbContentCategory> getContentCategoryList(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		return list;
	}

	@Override
	public TaoTaoResult createContentCategory(long parentId, String name) {
		// 插入新的分类
		Date date = new Date();
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setCreated(date);
		contentCategory.setUpdated(date);
		contentCategory.setParentId(parentId);
		contentCategory.setIsParent(false);
		contentCategory.setSortOrder(1);
		contentCategory.setStatus(1);
		contentCategoryMapper.insert(contentCategory);
		// 更新新的分类所在的父级目录，将其isparent改为1，说明他是一个父级目录
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbContentCategory tbContentCategory = list.get(0);
			if (!tbContentCategory.getIsParent()) {
				tbContentCategory.setIsParent(true);
				contentCategoryMapper.updateByPrimaryKey(tbContentCategory);
			}
			return TaoTaoResult.ok();
		}
		return null;
	}

	@Override
	public void updateContentCategory(long id, String name) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andIdEqualTo(id);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbContentCategory tbContentCategory = list.get(0);
			tbContentCategory.setName(name);
			contentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
		}
	}

	@Override
	public void deleteContentCategory(long id) {
		// 更新需要删除的分类，将状态改为2
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andIdEqualTo(id);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbContentCategory tbContentCategory = list.get(0);
			tbContentCategory.setStatus(2);
			contentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
			long parentId = tbContentCategory.getParentId();
			// 更新父级目录，如果当前的父级目录只有它一个，将其isparent修改未false；
			TbContentCategoryExample mExample = new TbContentCategoryExample();
			mExample.createCriteria().andParentIdEqualTo(parentId).andStatusEqualTo(1);
			List<TbContentCategory> mList = contentCategoryMapper.selectByExample(mExample);
			if (mList != null && mList.size() > 0) {

			} else {
				// 更新父级目录
				TbContentCategoryExample parentExample = new TbContentCategoryExample();
				parentExample.createCriteria().andIdEqualTo(parentId);
				List<TbContentCategory> parentList = contentCategoryMapper.selectByExample(parentExample);
				if (parentList != null && parentList.size() > 0) {
					TbContentCategory parent = parentList.get(0);
					if (parent.getIsParent()) {
						parent.setIsParent(false);
						contentCategoryMapper.updateByPrimaryKey(parent);
					}
				}
			}
		}
	}

}
