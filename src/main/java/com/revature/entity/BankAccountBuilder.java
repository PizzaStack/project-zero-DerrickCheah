package com.revature.entity;

import java.util.function.Consumer;

public class BankAccountBuilder {
	
	public int accountNumber;
	public String username;
	public double balance;
	public Account account;
	
	public BankAccountBuilder with(Consumer<BankAccountBuilder> builderFunction) {
		builderFunction.accept(this);
		return this;
	}
	
	public BankAccount buildBankAccount() {
		return new BankAccount(this.balance, this.accountNumber, this.username);
	}
}
