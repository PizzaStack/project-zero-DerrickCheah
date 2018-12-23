package com.revature.service;

import java.sql.Connection;
import java.util.Scanner;

public class AccountCreationMenu {

	private String username;
	private String username2;
	private String password;
	private String firstName;
	private String lastName;

	public AccountCreationMenu(Scanner input, Connection connection) {
		DAO dao = new DAO();

		System.out.print("Please enter your first name: ");
		this.firstName = input.next();

		System.out.println();

		System.out.print("Please enter your last name: ");
		this.lastName = input.next();

		System.out.println();

		boolean unique = false;
		while (!unique) {
			System.out.print("Please enter a unique username: ");
			this.username = input.next();
			unique = dao.checkUniqueUsername(connection, this.username);
		}

		System.out.println();

		System.out.print("Please enter a password: ");
		this.password = input.next();

		System.out.println();

		dao.setCustomerDBValues(connection, this.firstName, this.lastName, this.username, this.password);

		System.out.println("What type of account would you like to create?");
		System.out.println("\t1. Checking Account\n\t2. Savings Account\n\t3. Joint Account");

		String choice = input.next();
		choice = choice.toLowerCase();

		boolean b = true;

		do {
			if (choice.equals("1") || choice.contains("checking")) {
				b = false;
				dao.setCheckingDBValues(connection, this.username, "Pending");
				dao.setSavingsDBValues(connection, this.username, "Not Active");
				dao.setJointDBValues(connection, this.username, this.username, "Not Active");
			} else if (choice.equals("2") || choice.contains("savings")) {
				b = false;
				dao.setSavingsDBValues(connection, this.username, "Pending");
				dao.setCheckingDBValues(connection, this.username, "Not Active");
				dao.setJointDBValues(connection, this.username, this.username, "Not Active");
			} else if (choice.equals("3") || choice.contains("joint")) {
				// Enter information for second user
				b = false;
				dao.setJointDBValues(connection, this.username, this.username2, "Pending");
				dao.setCheckingDBValues(connection, this.username, "Not Active");
				dao.setSavingsDBValues(connection, this.username, "Not Active");
			} else {
				System.out.print("Please enter a valid input: ");
				input = new Scanner(System.in);
				choice = input.next();
				choice = choice.toLowerCase();
			}
		} while (b);

		System.out.println();
		System.out.println("Application Creation Succesful! Please Check Back Later.");
		System.out.println("Returning to main menu...");
		System.out.println();
	}
}
