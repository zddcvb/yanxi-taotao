package com.jollyclass.taotao.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * json数据格式的节点信息
 * @author 邹丹丹
 * @date 2017年7月21日 下午4:33:37
 * 
 */
public class CatNod {
	/**
	 * 分类的名称
	 */
	@JsonProperty("n")
	private String name;
	/**
	 * 分类的地址
	 */
	@JsonProperty("u")
	private String url;
	/**
	 * 分类的子内容
	 */
	@JsonProperty("i")
	private List<?> items;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}

}
