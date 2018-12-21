package com.revature.entity;

public class BankAccount {
	private int accountNumber;
	private String username;
	private double balance;

	public BankAccount(double balance, int accountNumber, String username) {
		this.accountNumber = accountNumber;
		this.setUsername(username);
		this.balance = balance;
	}
	
	public int getAccountNumber() {
		return this.accountNumber;
	}

	public double getBalance() {
		return this.balance;
	}

	public String getUsername() {
		return this.username;
	}

	public void setBalance(double amount) {
		this.balance = amount;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean deposit(double amount) {
		if (amount >= 0) {
			double newBalance = this.getBalance() + amount;
			this.setBalance(newBalance);
			return false;
		} else {
			System.out.print("Please enter a valid amount: ");
		}
		return true;
	}

	public boolean withdraw(double amount) {
		if (amount <= this.getBalance() && amount >= 0) {
			double newBalance = this.getBalance() - amount;
			this.setBalance(newBalance);
			return false;
		} else {
			System.out.print("Insufficient Funds. Please enter a valid amount: ");
		}
		return true;
	}

}
