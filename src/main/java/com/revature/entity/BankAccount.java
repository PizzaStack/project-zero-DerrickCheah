package com.revature.entity;

public class BankAccount {
	private String customerId;
	private double balance;

	public BankAccount(String customerId) {
		this.setCustomerId(customerId);
		this.balance = 0.00;
	}

	public double getBalance() {
		return this.balance;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setBalance(double amount) {
		this.balance = amount;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
