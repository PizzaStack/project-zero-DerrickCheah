package com.revature.entity;

public class SavingsAccount extends BankAccount {
	
	private final String ACCOUNTTYPE = "SAVINGS";

	public SavingsAccount(double balance, String username) {
		super(balance, username);
	}
	
	public String getAccountType() {
		return this.ACCOUNTTYPE;
	}

}
