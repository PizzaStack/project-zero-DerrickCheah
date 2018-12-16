package com.revature.entity;

public class SavingsAccount extends BankAccount {
	
	private final String ACCOUNTTYPE = "SAVINGS";

	public SavingsAccount(int accountNumber, String customerId, String password) {
		super(accountNumber, customerId, password);
	}
	
	public String getAccountType() {
		return this.ACCOUNTTYPE;
	}

}
