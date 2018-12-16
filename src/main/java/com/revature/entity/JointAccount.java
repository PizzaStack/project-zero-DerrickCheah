package com.revature.entity;

public class JointAccount extends BankAccount {
	
	private final String ACCOUNTTYPE = "JOINT";

	public JointAccount(int accountNumber, String customerId, String password) {
		super(accountNumber, customerId, password);
	}
	
	public String getAccountType() {
		return this.ACCOUNTTYPE;
	}

}
