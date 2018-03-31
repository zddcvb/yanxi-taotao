package com.jollyclass.taotao.portal.pojo;

/**
 * @author 邹丹丹
 * @date 2017年8月2日 上午11:14:36
 * 
 */
public class CartItem {
	private long id;
	private String title;
	private int num;
	private long price;
	private String image;
	private String[] images;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String[] getImages() {
		if (image != null) {
			String[] images = image.split(",");
			return images;
		}
		return null;
	}

}
