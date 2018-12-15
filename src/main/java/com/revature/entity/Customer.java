package com.revature.entity;

public class Customer {

	private String username;
	private String password;
	private String fullName;
	private Account account;

	public Customer() {
	}

	public Customer(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setUsername(String username) {
		this.username = username;
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
