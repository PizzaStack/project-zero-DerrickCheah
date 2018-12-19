package com.revature.entity;

public abstract class BankAccount {
	private int accountNumber;
	private String username;
	private String password;
	private double balance;
	private boolean pending;

	public BankAccount(int accountNumber, String username, String password) {
		this.accountNumber = accountNumber;
		this.setUsername(username);
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

	public String getUsername() {
		return this.username;
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

	public void setUsername(String username) {
		this.username = username;
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
