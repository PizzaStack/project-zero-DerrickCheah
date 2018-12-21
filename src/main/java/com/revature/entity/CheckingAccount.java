package com.revature.entity;

public class CheckingAccount extends BankAccount{
	
	private final String ACCOUNTTYPE = "CHECKING";

	public CheckingAccount(double balance, int accountNumber, String username) {
		super(balance, accountNumber, username);
	}
	
	public String getAccountType() {
		return this.ACCOUNTTYPE;
	}
}
