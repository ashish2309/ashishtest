package com.xebia.entity;

public class BillItem {

	private String itemName;
	
	private double price;
	
	private String itemType;

	private double discountedPrice;
	
	public BillItem(String itemName, Double price, String itemType) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.itemType = itemType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	@Override
	public String toString() {
		return "BillItem [itemName=" + itemName + ", price=" + price + ", itemType=" + itemType + ", discountedPrice="
				+ discountedPrice + "]";
	}		
}
