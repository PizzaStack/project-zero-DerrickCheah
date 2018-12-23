package com.revature.entity;

public class JointAccount extends BankAccount {
	
	private final String ACCOUNTTYPE = "JOINT";

	public JointAccount(double balance, String username) {
		super(balance, username);
	}
	
	public String getAccountType() {
		return this.ACCOUNTTYPE;
	}

}
