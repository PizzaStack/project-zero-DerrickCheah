package com.revature.entity;

public class Customer extends Person{
	
	private final String PERSONTYPE = "CUSTOMER";

	public Customer(String customerId, String password) {
		super(customerId, password);
	}
	
	public String getPersonType() {
		return this.PERSONTYPE;
	}

	public Person logIn() {
		return null;
	}
}
