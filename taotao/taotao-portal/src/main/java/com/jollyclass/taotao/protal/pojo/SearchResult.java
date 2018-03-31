package com.jollyclass.taotao.protal.pojo;

import java.util.List;

/**
 * @author 邹丹丹
 * @date 2017年7月26日 下午3:28:49
 * 
 */
public class SearchResult {
	/**
	 * 查询出来的数据
	 */
	private List<ItemResult> lists;
	/**
	 * 总数量
	 */
	private long totalCount;
	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 总页数
	 */
	private int pageCount;
	public List<ItemResult> getLists() {
		return lists;
	}
	public void setLists(List<ItemResult> lists) {
		this.lists = lists;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
}
