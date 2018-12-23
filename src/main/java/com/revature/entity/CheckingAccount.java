package com.revature.entity;

public class CheckingAccount extends BankAccount{
	
	private final String ACCOUNTTYPE = "CHECKING";

	public CheckingAccount(double balance, String username) {
		super(balance, username);
	}
	
	public String getAccountType() {
		return this.ACCOUNTTYPE;
	}
}
