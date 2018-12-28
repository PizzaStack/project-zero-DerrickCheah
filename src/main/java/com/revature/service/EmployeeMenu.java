package com.revature.service;

import java.sql.Connection;
import java.util.Scanner;

public class EmployeeMenu {

	public EmployeeMenu(Scanner input, Connection connection, String username) {
		String choice;
		DAO dao = new DAO();
		boolean loop = true;
		while (loop) {
			System.out.println("Please select an option below.");
			System.out.println("\t1. View Account Information" + "\n\t2. Withdraw Money" + "\n\t3. Deposit Money"
					+ "\n\t4. Transfer Money" + "\n\t5. Approve/Deny Accounts" + "\n\t6. Cancel Account"
					+ "\n\t7. Return");

			choice = input.next();
			choice = choice.toLowerCase();

			boolean b = true;
			while (b) {
				if (choice.equals("1") || choice.contains("view")) {
					System.out.print("Please enter customer's account number: ");
					int accountNumber = input.nextInt();
					System.out.println();
					boolean exists = dao.exists(connection, accountNumber);
					while (!exists) {
						System.out.print("Please enter a valid account number: ");
						accountNumber = input.nextInt();
						System.out.println();
						exists = dao.exists(connection, accountNumber);
					}

					String customerUsername = dao.getUsername(connection, accountNumber);
					dao.getAccountInformation(connection, customerUsername);
					b = false;

				} else if (choice.equals("2") || choice.contains("withdraw")) {
					if (dao.isAdmin(connection, username)) {
						System.out.print("Please enter customer's account number: ");
						int accountNumber = input.nextInt();
						System.out.println();
						boolean exists = dao.exists(connection, accountNumber);
						while (!exists) {
							System.out.print("Please enter a valid account number: ");
							accountNumber = input.nextInt();
							System.out.println();
							exists = dao.exists(connection, accountNumber);
						}

						String customerUsername = dao.getUsername(connection, accountNumber);
						String accountType = dao.getAccountType(connection, accountNumber);
						System.out.print("Enter the withdraw amount: ");
						double amount = input.nextDouble();
						System.out.println();
						dao.withdrawFunds(connection, customerUsername, amount, accountType, input);
					} else {
						System.out.println("You do not have admin privileges. Please contact a bank admin.");
					}
					b = false;

				} else if (choice.equals("3") || choice.contains("deposit")) {
					if (dao.isAdmin(connection, username)) {
						System.out.print("Please enter customer's account number: ");
						int accountNumber = input.nextInt();
						System.out.println();
						boolean exists = dao.exists(connection, accountNumber);
						while (!exists) {
							System.out.print("Please enter a valid account number: ");
							accountNumber = input.nextInt();
							System.out.println();
							exists = dao.exists(connection, accountNumber);
						}

						String customerUsername = dao.getUsername(connection, accountNumber);
						String accountType = dao.getAccountType(connection, accountNumber);
						System.out.print("Enter the deposit amount: ");
						double amount = input.nextDouble();
						System.out.println();
						dao.depositFunds(connection, customerUsername, amount, accountType, input);
					} else {
						System.out.println("You do not have admin privileges. Please contact a bank admin.");
					}
					b = false;

				} else if (choice.equals("4") || choice.contains("transfer")) {
					if (dao.isAdmin(connection, username)) {
						System.out.print("Please enter customer's account number: ");
						int accountNumber = input.nextInt();
						System.out.println();
						boolean exists = dao.exists(connection, accountNumber);
						while (!exists) {
							System.out.print("Please enter a valid account number: ");
							accountNumber = input.nextInt();
							System.out.println();
							exists = dao.exists(connection, accountNumber);
						}

						String customerUsername = dao.getUsername(connection, accountNumber);
						String accountType = dao.getAccountType(connection, accountNumber);

						System.out.print("Enter the account number you would like to transfer to: ");
						int accountNumberTo = input.nextInt();
						System.out.println();
						exists = dao.exists(connection, accountNumberTo);
						while (!exists) {
							System.out.print("Please enter a valid account number: ");
							accountNumberTo = input.nextInt();
							System.out.println();
							exists = dao.exists(connection, accountNumberTo);
						}
						System.out.print("Enter the transfer amount: ");
						double amount = input.nextDouble();
						System.out.println();
						dao.transferFunds(connection, customerUsername, amount, accountType, accountNumberTo, input);
					} else {
						System.out.println("You do not have admin privileges. Please contact a bank admin.");
					}
					b = false;

				} else if (choice.equals("5") || choice.contains("approve")) {
					dao.getPending(connection);
					System.out.println();
					System.out.print("Please enter the account you would like to approve or deny: ");
					int accountNumber = input.nextInt();
					String customerUsername = dao.getUsername(connection, accountNumber);
					dao.getAccountInformation(connection, customerUsername);
					System.out.println();
					System.out.print("Would you like to approve or deny? ");
					String approveOrDeny = input.next();
					approveOrDeny = approveOrDeny.toLowerCase();
					System.out.println();
					boolean a = true;
					while (a) {
						if (approveOrDeny.equals("approve") || approveOrDeny.contains("deny")) {
							a = false;
						} else {
							System.out.print("Invalid input. Would you like to approve or deny? ");
							approveOrDeny = input.next();
							approveOrDeny = approveOrDeny.toLowerCase();
							System.out.println();
						}
					}

					dao.approveOrDeny(connection, accountNumber, approveOrDeny);
					b = false;

				} else if (choice.equals("6") || choice.contains("cancel")) {
					if (dao.isAdmin(connection, username)) {
						System.out.print("Please enter customer's account number: ");
						int accountNumber = input.nextInt();
						System.out.println();
						boolean exists = dao.exists(connection, accountNumber);
						while (!exists) {
							System.out.print("Please enter a valid account number: ");
							accountNumber = input.nextInt();
							System.out.println();
							exists = dao.exists(connection, accountNumber);
						}

						String customerUsername = dao.getUsername(connection, accountNumber);
						String accountType = dao.getAccountType(connection, accountNumber);
						dao.cancelAccount(connection, customerUsername, accountType, input);
					} else {
						System.out.println("You do not have admin privileges. Please contact a bank admin.");
					}
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
