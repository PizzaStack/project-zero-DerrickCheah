package com.revature.entity;

public class SavingsAccount extends BankAccount {
	
	private final String ACCOUNTTYPE = "SAVINGS";

	public SavingsAccount(String customerId) {
		super(customerId);
	}
	
	public String getAccountType() {
		return this.ACCOUNTTYPE;
	}

}
