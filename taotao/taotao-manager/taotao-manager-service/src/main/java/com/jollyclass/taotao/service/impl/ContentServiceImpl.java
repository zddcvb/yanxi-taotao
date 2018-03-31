package com.jollyclass.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jollyclass.taotao.common.utils.ExceptionUtils;
import com.jollyclass.taotao.common.utils.HttpClientUtils;
import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.mapper.TbContentMapper;
import com.jollyclass.taotao.pojo.EUDataGridResult;
import com.jollyclass.taotao.pojo.TbContent;
import com.jollyclass.taotao.pojo.TbContentExample;
import com.jollyclass.taotao.service.ContentService;

/**
 * 內容的业务实现层
 * @author 邹丹丹
 * @date 2017年7月22日 上午11:33:06
 * 
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;

	@Override
	public EUDataGridResult getContentList(int page, int rows) {
		EUDataGridResult result = new EUDataGridResult();
		TbContentExample example = new TbContentExample();
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExample(example);
		result.setRows(list);
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaoTaoResult deleteContent(long[] ids) {
		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				contentMapper.deleteByPrimaryKey(id);
				//删除缓存数据
				Long categoryId = contentMapper.selectByPrimaryKey(id).getCategoryId();
				try {
					HttpClientUtils.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + categoryId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return TaoTaoResult.ok();
		}
		return null;
	}

	@Override
	public TaoTaoResult createContent(TbContent tbContent) {
		Date date = new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		contentMapper.insert(tbContent);
		return TaoTaoResult.ok();
	}

	@Override
	public TaoTaoResult updateContent(TbContent tbContent) {
		contentMapper.updateByPrimaryKeyWithBLOBs(tbContent);
		// 删除原有的缓存
		Long categoryId = tbContent.getCategoryId();
		try {
			HttpClientUtils.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + categoryId);
		} catch (Exception e) {
			e.printStackTrace();
			return TaoTaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return TaoTaoResult.ok();
	}

}
