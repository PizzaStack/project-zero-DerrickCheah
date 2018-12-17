package com.revature.entity;

public class Employee extends Person{
	
	public Employee(String customerId, String password) {
		super(customerId, password);
	}

	public String viewId(BankAccount account) {
		return account.getCustomerId();
	}
	
	public void approve() {
		//Set pending to false
	}

	public Person logIn() {
		return null;
	}

}
