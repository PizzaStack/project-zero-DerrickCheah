package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.entity.Customer;
import com.revature.entity.Person;

public class CustomerTest {
	
	@Test
	public void canCreateCustomerAccount() {
		String username = "derrick.cheah";
		String password = "Password123";
		String accountType = "Checking";
		Customer customer = new Customer(username, password);
	}
	
	@Test
	public void canLogIn() {
		String username = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(username, password);
		Person person = customer.logIn();
	}
	
	@Test
	public void canGetUsername() {
		String username = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(username, password);
		customer.setUsername(username);
		assertEquals(customer.getUsername(), username);
	}
	
	@Test
	public void canGetPassword() {
		String username = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(username, password);
		customer.setPassword(password);
		assertEquals(customer.getPassword(), password);
	}
	
	@Test
	public void canGetFullName() {
		String username = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(username, password);
		
		String firstName = "Derrick";
		String lastName = "Cheah";
		String fullName = firstName + " " + lastName;
		customer.setFullName(fullName);
		assertEquals(customer.getFullName(), fullName);
	}
	
	@Test
	public void canGetBalance() {
		String username = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(username, password);
		customer.getBalance();
	}
	
	@Test
	public void canDepositFunds() {
		String username = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(username, password);
		double amount = 50.00;
		customer.deposit(amount);
		assertEquals(customer.getBalance(), amount, 0.001);
	}
	
	@Test
	public void canNotDepositNegativeFunds() {
		String username = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(username, password);
		double amount = -50.00;
		customer.deposit(amount);
		assertEquals(customer.getBalance(), 0.00, 0.001);
	}
	
	@Test
	public void canWithdrawFunds() {
		String username = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(username, password);
		double depositAmount = 50.00;
		customer.deposit(depositAmount);
		double withdrawAmount = 25.00;
		customer.withdraw(withdrawAmount);
		assertEquals(customer.getBalance(), depositAmount - withdrawAmount, 0.001);
	}
	
	@Test
	public void canNotOverDrawFunds() {
		String username = "derrick.cheah";
		String password = "Password123";
		Customer customer = new Customer(username, password);
		double depositAmount = 50.00;
		customer.deposit(depositAmount);
		double withdrawAmount = 100.00;
		customer.withdraw(withdrawAmount);
		assertEquals(customer.getBalance(), depositAmount, 0.001);
	}
	
}
