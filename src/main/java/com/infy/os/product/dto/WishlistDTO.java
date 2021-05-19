package com.infy.os.product.dto;

public class WishlistDTO {

	Long buyerId;
	Long prodId;
	String productName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public WishlistDTO() {
		// TODO Auto-generated constructor stub
	}

	public WishlistDTO(Long buyerId, Long prodId) {
		this.buyerId = buyerId;
		this.prodId = prodId;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	@Override
	public String toString() {
		return "WishlistDTO [buyerId=" + buyerId + ", prodId=" + prodId + ", productName=" + productName + "]";
	}



}
