package com.revature.entity;

import java.util.function.Consumer;

public class BankAccountBuilder {
	
	public int accountNumber;
	public String customerId;
	public String password;
	public Account account;
	
	public BankAccountBuilder with(Consumer<BankAccountBuilder> builderFunction) {
		builderFunction.accept(this);
		return this;
	}
	
	public BankAccount buildBankAccount() {
		if (account.equals(Account.CHECKING)) {
			return new CheckingAccount(accountNumber, customerId, password);
		} else if (account.equals(Account.SAVINGS)) {
			return new SavingsAccount(accountNumber, customerId, password);
		} else {
			return new JointAccount(accountNumber, customerId, password);
		}
	}
}
