package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.entity.SavingsAccount;

public class SavingsAccountTest {
	
	@Test
	public void canCreateSavingsAccount() {
		String customerId = "derrick.cheah";
		new SavingsAccount(customerId);
	}
	
	@Test
	public void canGetAccountType() {
		String customerId = "derrick.cheah";
		SavingsAccount savings = new SavingsAccount(customerId);
		assertEquals(savings.getAccountType(), "SAVINGS");
	}
	
	@Test
	public void canDepositFunds() {
		String customerId = "derrick.cheah";
		SavingsAccount savings = new SavingsAccount(customerId);
		double amount = 50.43;
		savings.deposit(amount);
		assertEquals(savings.getBalance(), amount, 0.001);
	}
}
