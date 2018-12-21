package com.revature.entity;

public class SavingsAccount extends BankAccount {
	
	private final String ACCOUNTTYPE = "SAVINGS";

	public SavingsAccount(double balance, int accountNumber, String username) {
		super(balance, accountNumber, username);
	}
	
	public String getAccountType() {
		return this.ACCOUNTTYPE;
	}

}
