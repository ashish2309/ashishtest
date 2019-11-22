package com.xebia.service;

import java.util.Date;

import com.xebia.constants.Constants;
import com.xebia.discount.Discount;
import com.xebia.discount.impl.CustomerDiscount;
import com.xebia.discount.impl.EmployeeDiscount;
import com.xebia.discount.impl.NoDiscount;
import com.xebia.discount.impl.SpecialCustomerDiscount;
import com.xebia.entity.Bill;
import com.xebia.entity.User;
import com.xebia.exceptions.BadDataException;
import com.xebia.utility.DateUtility;

/**
 * 
 * @author ashish
 *
 * Factory class to generate discount type objects on the basis of user type.
 */
public class DiscountFactory {

	public static Discount createDiscount(Bill bill) throws BadDataException {
				
		User user = bill.getUser();
		
		if(user.getUserType() == null || user.getUserType().isEmpty())
			throw new BadDataException("User type is not defined");

		//create discount type object on the basis of user type.
		if(user.getUserType().equals(Constants.USER_TYPE_EMPLOYEE)) {
			return new EmployeeDiscount();
		} else if(user.getUserType().equals(Constants.USER_TYPE_CUSTOMER) && DateUtility.getDiffYears(user.getRegistrationDate(), new Date()) >= 2) {
			return new SpecialCustomerDiscount();
		} else if(user.getUserType().equals(Constants.USER_TYPE_CUSTOMER)) {
			return new CustomerDiscount();
		} else {
			System.out.println("No discount");
			return new NoDiscount();
		}
	}
}
