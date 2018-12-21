//package com.revature;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//
//import com.revature.entity.Account;
//import com.revature.entity.BankAccount;
//import com.revature.entity.BankAccountBuilder;
//
//public class BankAccountBuilderTest {
//	@SuppressWarnings("unused")
//	@Test
//	public void canCreateBankAccount() {
//		BankAccount account = new BankAccountBuilder().with($ -> {
//			$.username = "derrick.cheah";
//			$.password = "Password123";
//			$.account = Account.CHECKING;
//		}).buildBankAccount();
//	}
//
//	@Test
//	public void canDepositFunds() {
//		BankAccount account = new BankAccountBuilder().with($ -> {
//			$.username = "derrick.cheah";
//			$.password = "Password123";
//			$.account = Account.CHECKING;
//		}).buildBankAccount();
//
//		double amount = 50.12;
//		account.deposit(amount);
//		assertEquals(account.getBalance(), amount, 0.001);
//	}
//
//	@Test
//	public void canWithdrawFunds() {
//		BankAccount account = new BankAccountBuilder().with($ -> {
//			$.username = "derrick.cheah";
//			$.password = "Password123";
//			$.account = Account.CHECKING;
//		}).buildBankAccount();
//
//		double deposit = 50.12;
//		account.deposit(deposit);
//
//		double withdraw = 21.04;
//		account.withdraw(withdraw);
//
//		assertEquals(account.getBalance(), deposit - withdraw, 0.001);
//	}
//
//	@Test
//	public void canGetAccountType() {
//		BankAccount account = new BankAccountBuilder().with($ -> {
//			$.username = "derrick.cheah";
//			$.password = "Password123";
//			$.account = Account.JOINT;
//		}).buildBankAccount();
//
//		assertEquals(account.getAccountType(), "JOINT");
//	}
//}
