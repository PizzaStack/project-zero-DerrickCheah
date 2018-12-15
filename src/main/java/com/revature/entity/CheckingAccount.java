package com.revature.entity;

public class CheckingAccount extends BankAccount{
	
	private final String ACCOUNTTYPE = "CHECKING";

	public CheckingAccount(String customerId) {
		super(customerId);
	}
	
	public String getAccountType() {
		return this.ACCOUNTTYPE;
	}
}
