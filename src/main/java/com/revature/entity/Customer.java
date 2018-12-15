package com.revature.entity;

public class Customer {

	private String customerId;
	private String password;
	private String fullName;

	public Customer(String customerId, String password) {
		this.customerId = customerId;
		this.password = password;
	}

	public String getId() {
		return this.customerId;
	}

	public String getPassword() {
		return this.password;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setId(String customerId) {
		this.customerId = customerId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Person logIn() {
		return null;
	}
}
