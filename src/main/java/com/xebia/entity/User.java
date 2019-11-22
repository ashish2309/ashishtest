package com.xebia.entity;

import java.util.Date;

public class User {

	private String userName;
	private String userType;
	private Date registrationDate;
	
	public User(String userName, String userType, Date registrationDate) {
		super();
		this.userName = userName;
		this.userType = userType;
		this.registrationDate = registrationDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}	
}
