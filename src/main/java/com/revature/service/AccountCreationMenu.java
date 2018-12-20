package com.revature.service;

import java.sql.Connection;
import java.util.Scanner;

import com.revature.entity.Account;
import com.revature.entity.BankAccount;
import com.revature.entity.BankAccountBuilder;

public class AccountCreationMenu {

	public static int accountNumber = 1;
	private String username;
	private String username2;
	private String password;
	private String firstName;
	private String lastName;
	private Account accountType;
	private BankAccount bankAccount;

	public AccountCreationMenu(Scanner input, Connection connection) {

		System.out.print("Please enter your first name: ");
		this.firstName = input.nextLine();

		System.out.println();

		System.out.print("Please enter your last name: ");
		this.lastName = input.nextLine();

		System.out.println();

		System.out.print("Please enter a unique username: ");
		this.username = input.nextLine();

		System.out.println();

		System.out.print("Please enter a password: ");
		this.password = input.nextLine();

		System.out.println();

		DAO dao = new DAO();
		dao.setCustomerDBValues(connection, this.firstName, this.lastName, this.username, this.password);

		System.out.println("What type of account would you like to create?");
		System.out.println("\t1. Checking Account\n\t2. Savings Account\n\t3. Joint Account");

		String choice = input.nextLine();
		choice = choice.toLowerCase();

		boolean b = true;

		do {
			if (choice.equals("1") || choice.equals("checking") || choice.equals("checking account")) {
				this.accountType = Account.CHECKING;
				b = false;
				dao.setCheckingDBValues(connection, this.username, AccountCreationMenu.accountNumber, "Pending");
				dao.setSavingsDBValues(connection, this.username, AccountCreationMenu.accountNumber, "Not Active");
				dao.setJointDBValues(connection, this.username, this.username, AccountCreationMenu.accountNumber,
						"Not Active");
			} else if (choice.equals("2") || choice.equals("savings") || choice.equals("savings account")) {
				this.accountType = Account.SAVINGS;
				b = false;
				dao.setSavingsDBValues(connection, this.username, AccountCreationMenu.accountNumber, "Pending");
				dao.setCheckingDBValues(connection, this.username, AccountCreationMenu.accountNumber, "Not Active");
				dao.setJointDBValues(connection, this.username, this.username, AccountCreationMenu.accountNumber,
						"Not Active");
			} else if (choice.equals("3") || choice.equals("joint") || choice.equals("joint account")) {
				this.accountType = Account.JOINT;
				// Enter information for second user
				b = false;
				dao.setJointDBValues(connection, this.username, this.username2, AccountCreationMenu.accountNumber,
						"Pending");
				dao.setCheckingDBValues(connection, this.username, AccountCreationMenu.accountNumber, "Not Active");
				dao.setSavingsDBValues(connection, this.username, AccountCreationMenu.accountNumber, "Not Active");
			} else {
				System.out.print("Please enter a valid input: ");
				input = new Scanner(System.in);
				choice = input.nextLine();
				choice = choice.toLowerCase();
			}
		} while (b);

		System.out.println();

		this.bankAccount = new BankAccountBuilder().with($ -> {
			$.accountNumber = AccountCreationMenu.accountNumber;
			$.username = this.username;
			$.password = this.password;
			$.account = this.accountType;
		}).buildBankAccount();

		System.out.println("Application Creation Succesful! Please Check Back Later.");
		System.out.println("Returning to main menu...");
		System.out.println();
	}

	public BankAccount getAccount() {
		return this.bankAccount;
	}
}
