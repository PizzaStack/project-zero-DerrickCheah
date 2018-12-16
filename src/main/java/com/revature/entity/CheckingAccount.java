package com.revature.entity;

public class CheckingAccount extends BankAccount{
	
	private final String ACCOUNTTYPE = "CHECKING";

	public CheckingAccount(int accountNumber, String customerId, String password) {
		super(accountNumber, customerId, password);
	}
	
	public String getAccountType() {
		return this.ACCOUNTTYPE;
	}
}
