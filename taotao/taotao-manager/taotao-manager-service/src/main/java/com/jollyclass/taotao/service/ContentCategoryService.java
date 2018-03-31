package com.jollyclass.taotao.service;

import java.util.List;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.pojo.TbContentCategory;

/**
 * 分类信息的业务层
 * @author 邹丹丹
 * @date 2017年7月21日 下午6:57:52
 * 
 */
public interface ContentCategoryService {
	/**
	 * 获得所有的分类信息
	 * @param parentId
	 * @return
	 */
	public List<TbContentCategory> getContentCategoryList(long parentId);
	/**
	 * 创建新的分类
	 * @param parentId 新建分类所在的父级目录
	 * @param name 新建分类的名称
	 * @return TaoTaoResult对象
	 */
	public TaoTaoResult createContentCategory(long parentId,String name);
	/**
	 * 重命名当前的分类
	 * @param id 需要修改的分类id
	 * @param name 修改后的名称
	 */
	public void updateContentCategory(long id,String name);
	/**
	 * 刪除指定id号的分类，同步更新它的父级内容
	 * @param parentId 父级id号
	 * @param id 需要删除的id
	 * @return TaoTaoResult对象
	 */
	public void deleteContentCategory(long id);
}
