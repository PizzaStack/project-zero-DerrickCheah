package com.revature.entity;

import java.util.function.Consumer;

public class BankAccountBuilder {
	
	public int accountNumber;
	public String username;
	public String password;
	public Account account;
	
	public BankAccountBuilder with(Consumer<BankAccountBuilder> builderFunction) {
		builderFunction.accept(this);
		return this;
	}
	
	public BankAccount buildBankAccount() {
		if (account.equals(Account.CHECKING)) {
			return new CheckingAccount(accountNumber, username, password);
		} else if (account.equals(Account.SAVINGS)) {
			return new SavingsAccount(accountNumber, username, password);
		} else {
			return new JointAccount(accountNumber, username, password);
		}
	}
}
