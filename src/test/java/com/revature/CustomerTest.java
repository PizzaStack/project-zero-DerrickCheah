package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.entity.Customer;
import com.revature.entity.Person;

public class CustomerTest {
	
	@Test
	public void canCreateCustomerAccount() {
		String customerId = "derrick.cheah";
		String password = "Password123";
		String accountType = "Checking";
		Customer customer = new Customer(customerId, password);
	}
	
	@Test
	public void canLogIn() {
		String customerId = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(customerId, password);
		Person person = customer.logIn();
	}
	
	@Test
	public void canGetUsername() {
		String customerId = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(customerId, password);
		customer.setId(customerId);
		assertEquals(customer.getId(), customerId);
	}
	
	@Test
	public void canGetPassword() {
		String customerId = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(customerId, password);
		customer.setPassword(password);
		assertEquals(customer.getPassword(), password);
	}
	
	@Test
	public void canGetFullName() {
		String customerId = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(customerId, password);
		
		String firstName = "Derrick";
		String lastName = "Cheah";
		String fullName = firstName + " " + lastName;
		customer.setFullName(fullName);
		assertEquals(customer.getFullName(), fullName);
	}
	
//	@Test
//	public void canGetBalance() {
//		String customerId = "derrick.cheah";
//		String password = "Password123";
//		Customer customer = new Customer(customerId, password);
//		customer.getBalance();
//	}
//	
//	@Test
//	public void canDepositFunds() {
//		String customerId = "derrick.cheah";
//		String password = "Password123";
//		Customer customer = new Customer(customerId, password);
//		double amount = 50.00;
//		customer.deposit(amount);
//		assertEquals(customer.getBalance(), amount, 0.001);
//	}
//	
//	@Test
//	public void canNotDepositNegativeFunds() {
//		String customerId = "derrick.cheah";
//		String password = "Password123";
//		Customer customer = new Customer(customerId, password);
//		double amount = -50.00;
//		customer.deposit(amount);
//		assertEquals(customer.getBalance(), 0.00, 0.001);
//	}
//	
//	@Test
//	public void canWithdrawFunds() {
//		String customerId = "derrick.cheah";
//		String password = "Password123";
//		Customer customer = new Customer(customerId, password);
//		double depositAmount = 50.00;
//		customer.deposit(depositAmount);
//		double withdrawAmount = 25.00;
//		customer.withdraw(withdrawAmount);
//		assertEquals(customer.getBalance(), depositAmount - withdrawAmount, 0.001);
//	}
//	
//	@Test
//	public void canNotOverDrawFunds() {
//		String customerId = "derrick.cheah";
//		String password = "Password123";
//		Customer customer = new Customer(customerId, password);
//		double depositAmount = 50.00;
//		customer.deposit(depositAmount);
//		double withdrawAmount = 100.00;
//		customer.withdraw(withdrawAmount);
//		assertEquals(customer.getBalance(), depositAmount, 0.001);
//	}
	
}
