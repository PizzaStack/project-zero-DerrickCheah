package com.revature.service;

import java.sql.Connection;
import java.util.Scanner;

public class CustomerMenu {

	public CustomerMenu(Scanner input, Connection connection, String username) {
		Scanner readAccounts;
		String choice;
		DAO dao = new DAO();
		boolean loop = true;
		while (loop) {
			System.out.println("Please select an option below.");
			System.out.println("\t1. View Account Information" + "\n\t2. Edit Account Information"
					+ "\n\t3. Withdraw Money" + "\n\t4. Deposit Money" + "\n\t5. Transfer Money"
					+ "\n\t6. Apply for a New Account" + "\n\t7. Return to Main Menu");

			choice = input.next();
			choice = choice.toLowerCase();
			boolean b = true;
			while (b) {
				if (choice.equals("1") || choice.contains("view")) {
					dao.getAccountInformation(connection, username);
					b = false;
				} else if (choice.equals("2") || choice.contains("edit")) {
					b = false;
				} else if (choice.equals("3") || choice.contains("withdraw")) {
					System.out.println("Select the account you would like to withdraw from.");
					String accounts = dao.getActiveAccounts(connection, username);
					readAccounts = new Scanner(accounts);
					int counter = 0;
					while (readAccounts.hasNext()) {
						System.out.println("\t" + readAccounts.next());
						counter++;
					}
					if (counter == 0) {
						System.out.println("\tNo active accounts!");
						break;
					}
					String accountChoice = input.next();
					accountChoice = accountChoice.toLowerCase();
					boolean c = true;
					while (c) {
						if (accountChoice.contains("checking") || accountChoice.contains("savings")
								|| accountChoice.contains("joint")) {
							c = false;
						} else {
							System.out.print("Please enter a valid account: ");
							accountChoice = input.next();
							System.out.println();
						}
					}
					System.out.print("Enter the withdraw amount: ");
					double amount = input.nextDouble();
					System.out.println();
					dao.withdrawFunds(connection, username, amount, accountChoice, input);
					b = false;
				} else if (choice.equals("4") || choice.contains("deposit")) {
					System.out.println("Select the account you would like to deposit to.");
					String accounts = dao.getActiveAccounts(connection, username);
					readAccounts = new Scanner(accounts);
					int counter = 0;
					while (readAccounts.hasNext()) {
						System.out.println("\t" + readAccounts.next());
						counter++;
					}
					if (counter == 0) {
						System.out.println("\tNo active accounts!");
						break;
					}
					String accountChoice = input.next();
					accountChoice = accountChoice.toLowerCase();
					boolean c = true;
					while (c) {
						if (accountChoice.contains("checking") || accountChoice.contains("savings")
								|| accountChoice.contains("joint")) {
							c = false;
						} else {
							System.out.print("Please enter a valid account: ");
							accountChoice = input.next();
							System.out.println();
						}
					}
					System.out.print("Enter the deposit amount: ");
					double amount = input.nextDouble();
					System.out.println();
					dao.depositFunds(connection, username, amount, accountChoice, input);
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
					choice = input.next();
					choice = choice.toLowerCase();
				}
			}

			System.out.println();
		}
	}

}
