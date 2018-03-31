package com.jollyclass.taotao.service;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.EUDataGridResult;
import com.jollyclass.taotao.pojo.TbContent;

/**
 * 内容的业务层
 * @author 邹丹丹
 * @date 2017年7月22日 上午11:31:32
 * 
 */
public interface ContentService {
	/**
	 * 查询所有的内容信息
	 * @param page 当前页
	 * @param rows 当前页显示的数据数量
	 * @return EUDataGridResult对象
	 */
	EUDataGridResult getContentList(int page,int rows);
	/**
	 * 删除功能
	 * @param ids 需要删除的id数据
	 * @return TaoTaoResult对象
	 */
	TaoTaoResult deleteContent(long[] ids);
	/**
	 * 添加新的content
	 * @param tbContent 需要添加的content
	 * @return TaoTaoResult對象
	 */
	TaoTaoResult createContent(TbContent tbContent);
	/**
	 * 编辑content
	 * @param tbContent 需要添加的content
	 * @return TaoTaoResult對象
	 */
	TaoTaoResult updateContent(TbContent tbContent);
}
