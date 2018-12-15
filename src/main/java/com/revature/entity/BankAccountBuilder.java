package com.revature.entity;

import java.util.function.Consumer;

public class BankAccountBuilder {
	
	public String customerId;
	public Account account = Account.CHECKING;
	
	public BankAccountBuilder with(Consumer<BankAccountBuilder> builderFunction) {
		builderFunction.accept(this);
		return this;
	}
	
	public BankAccount buildBankAccount() {
		if (account.equals(Account.CHECKING)) {
			return new CheckingAccount(customerId);
		} else if (account.equals(Account.SAVINGS)) {
			return new SavingsAccount(customerId);
		} else {
			return new JointAccount(customerId);
		}
	}
}
