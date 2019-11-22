package com.xebia.discount.impl;

import com.xebia.discount.Discount;
import com.xebia.entity.Bill;

/*
 * This class will called when no percentage based discount condition will be match.
 */
public class NoDiscount implements Discount {

	@Override
	public Bill applyDiscount(Bill bill) {
		bill.getItems().stream().forEach(item -> {
			item.setDiscountedPrice(item.getPrice());			
		});
		return bill;
	}
}
