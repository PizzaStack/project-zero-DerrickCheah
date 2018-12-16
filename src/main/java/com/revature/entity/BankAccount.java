package com.revature.entity;

public abstract class BankAccount {
	private int accountNumber;
	private String customerId;
	private String password;
	private double balance;
	private boolean pending;

	public BankAccount(int accountNumber, String customerId, String password) {
		this.accountNumber = accountNumber;
		this.setCustomerId(customerId);
		this.setPassword(password);
		this.balance = 0.00;
		this.pending = true;
	}

	public abstract String getAccountType();
	
	public int getAccountNumber() {
		return this.accountNumber;
	}

	public double getBalance() {
		return this.balance;
	}

	public String getCustomerId() {
		return this.customerId;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean getPending() {
		return this.pending;
	}

	public void setBalance(double amount) {
		this.balance = amount;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public void deposit(double amount) {
		if (amount >= 0) {
			double newBalance = this.getBalance() + amount;
			this.setBalance(newBalance);
		} else {
			System.out.println("Please enter a valid amount.");
		}
	}

	public void withdraw(double amount) {
		if (amount <= this.getBalance()) {
			double newBalance = this.getBalance() - amount;
			this.setBalance(newBalance);
		} else {
			System.out.println("Insufficient Funds.");
		}
	}

}
