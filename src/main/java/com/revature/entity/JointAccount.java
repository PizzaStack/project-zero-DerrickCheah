package com.revature.entity;

public class JointAccount extends BankAccount {
	
	private final String ACCOUNTTYPE = "JOINT";

	public JointAccount(String customerId) {
		super(customerId);
	}
	
	public String getAccountType() {
		return this.ACCOUNTTYPE;
	}

}
