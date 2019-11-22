package com.xebia.entity;

import java.util.List;

public class Bill {

	private Integer billNumber;
		
	private double payableAmount;
	
	private double totalAmount;
	
	private User user;
	
	private List<BillItem> items;
		
	public Bill(Integer billNumber, User user, List<BillItem> items) {
		super();
		this.billNumber = billNumber;
		this.user = user;
		this.items = items;
	}

	public Integer getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(Integer billNumber) {
		this.billNumber = billNumber;
	}

	public double getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}

	public List<BillItem> getItems() {
		return items;
	}

	public void setItems(List<BillItem> items) {
		this.items = items;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Bill [billNumber=" + billNumber + ", payableAmount=" + payableAmount + ", items=" + items + "]";
	}	
}