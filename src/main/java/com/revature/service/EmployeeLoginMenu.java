package com.revature.service;

import java.sql.Connection;
import java.util.Scanner;

public class EmployeeLoginMenu {
	
	private String username;
	private String password;

	public EmployeeLoginMenu(Scanner input, Connection connection) {
		boolean loop = true;
		while (loop) {
			System.out.print("Please enter your username: ");
			this.username = input.next();

			System.out.println();

			System.out.print("Please enter your password: ");
			this.password = input.next();

			DAO dao = new DAO();
			boolean login = dao.checkEmployeeLogin(connection, this.username, this.password);
			if (login) {
				System.out.println("Login Successful!");
				System.out.println();
				loop = false;
			} else {
				System.out.println("Incorrect username or password.");
				System.out.println();
			}
		}
	}

}
