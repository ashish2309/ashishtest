package com.xebia;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.xebia.constants.Constants;
import com.xebia.entity.Bill;
import com.xebia.entity.BillItem;
import com.xebia.entity.User;
import com.xebia.exceptions.BadDataException;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

	@InjectMocks
	Application application;
	
	/*
	 * Test process bill for user type employee
	 */
	@Test
	public void testProcessBillForEmployee() throws BadDataException {
		
		User user = new User("John", Constants.USER_TYPE_EMPLOYEE, new Date());		
		BillItem item1 = new BillItem("Item1", 200.0, Constants.ITEM_TYPE_ELECTRONICS);
		BillItem item2 = new BillItem("Item2", 300.0, Constants.ITEM_TYPE_ELECTRONICS);
		List<BillItem> items = new ArrayList<BillItem>();
		items.add(item1);
		items.add(item2);
		Bill bill = new Bill(1234, user, items);

		application.processBill(bill);		

		assertEquals(335.0, bill.getPayableAmount(), 0);
		assertEquals(140.0, bill.getItems().get(0).getDiscountedPrice(), 0);
		assertEquals(210.0, bill.getItems().get(1).getDiscountedPrice(), 0);
	}

	/*
	 * Test process bill for user type customer
	 */
	@Test
	public void testProcessBillForCustomer() throws BadDataException {
		
		User user = new User("John", Constants.USER_TYPE_CUSTOMER, new Date());		
		BillItem item1 = new BillItem("Item1", 200.0, Constants.ITEM_TYPE_ELECTRONICS);
		BillItem item2 = new BillItem("Item2", 300.0, Constants.ITEM_TYPE_FURNITURE);
		List<BillItem> items = new ArrayList<BillItem>();
		items.add(item1);
		items.add(item2);
		Bill bill = new Bill(1234, user, items);

		application.processBill(bill);

		assertEquals(430.0, bill.getPayableAmount(), 0);
		assertEquals(180.0, bill.getItems().get(0).getDiscountedPrice(), 0);
		assertEquals(270.0, bill.getItems().get(1).getDiscountedPrice(), 0);
	}

	/*
	 * Test process bill for users who is a customer since last 2 years
	 */
	@Test
	public void testProcessBillForTwoYearOldCustomer() throws BadDataException {
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.add(Calendar.DATE, -1095); //date 3 years ago from now
		
		User user = new User("John", Constants.USER_TYPE_CUSTOMER, cal.getTime());
		BillItem item1 = new BillItem("Item1", 200.0, Constants.ITEM_TYPE_ELECTRONICS);
		BillItem item2 = new BillItem("Item2", 300.0, Constants.ITEM_TYPE_ELECTRONICS);
		List<BillItem> items = new ArrayList<BillItem>();
		items.add(item1);
		items.add(item2);
		Bill bill = new Bill(1234, user, items);

		application.processBill(bill);		

		assertEquals(455.0, bill.getPayableAmount(), 0);
		assertEquals(190.0, bill.getItems().get(0).getDiscountedPrice(), 0);
		assertEquals(285.0, bill.getItems().get(1).getDiscountedPrice(), 0);
	}

	/*
	 * Test process bill for grocery item. Percentage based discount should not be applied
	 * on grocery items
	 */
	@Test
	public void testProcessBillForGroceryItem() throws BadDataException {
				
		User user = new User("John", Constants.USER_TYPE_CUSTOMER, new Date());
		BillItem item1 = new BillItem("Item1", 200.0, Constants.ITEM_TYPE_ELECTRONICS);
		BillItem item2 = new BillItem("Item2", 300.0, Constants.ITEM_TYPE_GROCERY);
		List<BillItem> items = new ArrayList<BillItem>();
		items.add(item1);
		items.add(item2);
		Bill bill = new Bill(1234, user, items);

		application.processBill(bill);

		assertEquals(460.0, bill.getPayableAmount(), 0);
		assertEquals(180.0, bill.getItems().get(0).getDiscountedPrice(), 0);
		assertEquals(300.0, bill.getItems().get(1).getDiscountedPrice(), 0);
	}
	
	/*
	 * Test processBill when user type is not provided.
	 */
	@Test(expected = BadDataException.class)
	public void testProcessBillForUserTypeNull() throws BadDataException {
				
		User user = new User("John", null , new Date());
		BillItem item1 = new BillItem("Item1", 200.0, Constants.ITEM_TYPE_ELECTRONICS);
		BillItem item2 = new BillItem("Item2", 300.0, Constants.ITEM_TYPE_GROCERY);
		List<BillItem> items = new ArrayList<BillItem>();
		items.add(item1);
		items.add(item2);
		Bill bill = new Bill(1234, user, items);
		
		application.processBill(bill);
	}	
}