package com.xebia;

import com.xebia.discount.Discount;
import com.xebia.discount.impl.AmountBasedDiscount;
import com.xebia.entity.Bill;
import com.xebia.entity.BillItem;
import com.xebia.exceptions.BadDataException;
import com.xebia.service.DiscountFactory;

/**
 * 
 * @author Ashish
 *
 * This class is the root class to handle all the discount related
 * operations on the provided bill. A chain of responsibility is being
 * implemented in this class which process a provided bill and pass it
 * through several services.
 */
public class Application {
	
	/*
	 * This method is the initial method to process a bill.
	 */
	public Bill processBill(Bill bill) throws BadDataException {
		
		
		/*
		 * Get required discount type object on the basis of type of customer
		 */
		Discount percentageDiscount = DiscountFactory.createDiscount(bill);		
		
		// apply discount strategy for the fetch discount type
		percentageDiscount.applyDiscount(bill);
		
		// set total payable amount by summing up the discounted amount of all the bill items
		calculatePayableAmount(bill);
				
		Discount amountBasedDiscount = new AmountBasedDiscount();
		
		// apply amount based discount on the bill. An amount based discount will only be applied
		// a user cross a specified limit of the amount. Ex 100 in this example.
		amountBasedDiscount.applyDiscount(bill);
		
		return bill;
	}
	
	private void calculatePayableAmount(Bill bill) {
		
		double payableAmount = 0.0;
		for(BillItem item : bill.getItems()) {
			payableAmount+=item.getDiscountedPrice();
		}
		bill.setPayableAmount(payableAmount);
	}	
}