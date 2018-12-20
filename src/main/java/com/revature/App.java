package com.revature;

import java.sql.Connection;
import java.util.Scanner;

import com.revature.service.AccountCreationMenu;
import com.revature.service.CreateConnection;
import com.revature.service.CustomerLoginMenu;
import com.revature.service.EmployeeLoginMenu;

public class App {
	public static void main(String[] args) {

		CreateConnection createConnection = new CreateConnection();
		Connection connection = createConnection.getConnection();

		Scanner input = new Scanner(System.in);

		boolean bool = true;
		while (bool) {
			System.out.println("Welcome to Derrick's Bank! Please select an option to begin.");
			System.out.println("\t1. Apply for a new account" + "\n\t2. Customer log in" + "\n\t3. Employee log in"
					+ "\n\t4. Exit");

			String n = input.nextLine();

			if (n.equals("1")) {
				new AccountCreationMenu(input, connection);
			} else if (n.equals("2")) {
				new CustomerLoginMenu(input, connection);
			} else if (n.equals("3")) {
				new EmployeeLoginMenu(input, connection);
			} else if (n.equals("4")) {
				System.out.println("Thank you for banking with us!");
				bool = false;
			}
		}
		createConnection.closeConnection(connection);
		input.close();
	}
}
