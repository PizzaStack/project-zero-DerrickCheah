package com.revature.entity;

public class JointAccount extends BankAccount {
	
	private final String ACCOUNTTYPE = "JOINT";

	public JointAccount(double balance, int accountNumber, String username) {
		super(balance, accountNumber, username);
	}
	
	public String getAccountType() {
		return this.ACCOUNTTYPE;
	}

}
