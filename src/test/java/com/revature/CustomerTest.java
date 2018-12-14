package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CustomerTest {
	
	@Test
	public void canCreateCustomerAccount() {
		Customer customer = new Customer();
	}
	
	@Test
	public void canCreateCustomerAccountWithUserAndPass() {
		String username = "derrick.cheah";
		String password = "Password123";
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
		
	}
	
}
