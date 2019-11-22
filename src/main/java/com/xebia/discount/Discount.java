package com.xebia.discount;

import com.xebia.entity.Bill;

/*
 * Common interface to be implemented by all the type of discounts
 */
public interface Discount {

	Bill applyDiscount(Bill bill);
}
