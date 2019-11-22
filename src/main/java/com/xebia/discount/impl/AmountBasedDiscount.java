package com.xebia.discount.impl;

import com.xebia.constants.Constants;
import com.xebia.discount.Discount;
import com.xebia.entity.Bill;

/**
 * AmountBasedDiscount is a special type of discount which will only be applicable if
 * a bill crosses a specified limit of amount.
 **/
public class AmountBasedDiscount implements Discount {

	@Override
	public Bill applyDiscount(Bill bill) {
		
		Double payableAmount = bill.getPayableAmount();
		Double totalHundreds = payableAmount / Constants.AMOUNT_FOR_AMOUNT_BASED_DISCOUNT;
		double discountedAmount = bill.getPayableAmount() - (totalHundreds.intValue() * Constants.AMOUNT_BASED_DISCOUNT_VALUE);
		bill.setPayableAmount(discountedAmount);
		return bill;
	}
}
