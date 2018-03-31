package com.jollyclass.taotao.pojo;

import java.util.List;

/**
 * easyui的datagrid的数据类
 * @author 邹丹丹
 * @date 2017年7月17日 上午11:04:41
 * 
 */
public class EUDataGridResult {
	/**
	 * 数据的总数量
	 */
	private long total;
	/**
	 * 所传数据的集合
	 */
	private List<?> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
