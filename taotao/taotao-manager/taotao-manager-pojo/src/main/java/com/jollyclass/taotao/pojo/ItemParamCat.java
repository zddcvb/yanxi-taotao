package com.jollyclass.taotao.pojo;

import java.util.Date;

/**
 * @author 邹丹丹
 * @date 2017年7月20日 下午2:45:06
 * 
 */
public class ItemParamCat {
	private long id;
	private long itemCatId;
	private String itemCatName;
	private String paramData;
	private Date created;
	private Date updated;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getItemCatId() {
		return itemCatId;
	}
	public void setItemCatId(long itemCatId) {
		this.itemCatId = itemCatId;
	}
	public String getItemCatName() {
		return itemCatName;
	}
	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}
	public String getParamData() {
		return paramData;
	}
	public void setParamData(String paramData) {
		this.paramData = paramData;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
}
