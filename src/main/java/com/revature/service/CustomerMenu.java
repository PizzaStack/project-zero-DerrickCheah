package com.revature.service;

import java.sql.Connection;
import java.util.Scanner;

public class CustomerMenu {

	public CustomerMenu(Scanner input, Connection connection, String username) {
		boolean loop = true;
		while (loop) {
			System.out.println("Please select an option below.");
			System.out.println("\t1. View Account Information" + "\n\t2. Edit Account Information"
					+ "\n\t3. Withdraw Money" + "\n\t4. Deposit Money" + "\n\t5. Transfer Money"
					+ "\n\t6. Apply for a New Account" + "\n\t7. Return to Main Menu");

			String choice = input.nextLine();
			choice = choice.toLowerCase();
			
			DAO dao = new DAO();
			boolean b = true;
			do {
				if (choice.equals("1") || choice.contains("view")) {
					b = false;
					dao.getAccountInformation(connection, username);
				} else if (choice.equals("2") || choice.contains("edit")) {
					b = false;
				} else if (choice.equals("3") || choice.contains("withdraw")) {
					b = false;
				} else if (choice.equals("4") || choice.contains("deposit")) {
					b = false;
				} else if (choice.equals("5") || choice.contains("transfer")) {
					b = false;
				} else if (choice.equals("6") || choice.contains("apply")) {
					b = false;
				} else if (choice.equals("7") || choice.contains("return")) {
					b = false;
					loop = false;
				} else {
					System.out.print("Please enter a valid input: ");
					input = new Scanner(System.in);
					choice = input.nextLine();
					choice = choice.toLowerCase();
				}
			} while (b);

			System.out.println();
		}
	}

}
