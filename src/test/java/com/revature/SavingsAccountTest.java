package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.entity.SavingsAccount;

public class SavingsAccountTest {
	
	@Test
	public void canCreateSavingsAccount() {
		String customerId = "derrick.cheah";
		String password = "Password123";
		int accountNumber = 0;
		new SavingsAccount(accountNumber, customerId, password);
	}
	
	@Test
	public void canGetAccountType() {
		String customerId = "derrick.cheah";
		String password = "Password123";
		int accountNumber = 0;
		SavingsAccount savings = new SavingsAccount(accountNumber, customerId, password);
		assertEquals(savings.getAccountType(), "SAVINGS");
	}
	
	@Test
	public void canDepositFunds() {
		String customerId = "derrick.cheah";
		String password = "Password123";
		int accountNumber = 0;
		SavingsAccount savings = new SavingsAccount(accountNumber, customerId, password);
		double amount = 50.43;
		savings.deposit(amount);
		assertEquals(savings.getBalance(), amount, 0.001);
	}
}
