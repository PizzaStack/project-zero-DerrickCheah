package com.revature.service;

import java.sql.Connection;
import java.util.Scanner;

public class CustomerLoginMenu {

	private String username;
	private String password;

	public CustomerLoginMenu(Scanner input, Connection connection) {
		boolean loop = true;
		while (loop) {
			System.out.print("Please enter your username: ");
			this.username = input.nextLine();

			System.out.println();

			System.out.print("Please enter your password: ");
			this.password = input.nextLine();

			DAO dao = new DAO();
			boolean login = dao.checkCustomerLogin(connection, this.username, this.password);
			if (login) {
				System.out.println("Login Successful!");
				System.out.println();
				new CustomerMenu(input, connection, this.username);
				loop = false;
			} else {
				System.out.println("Incorrect username or password.");
				System.out.println();
			}
		}
	}
}
