package com.revature.service;

import java.sql.Connection;
import java.util.Scanner;

import com.revature.entity.Account;
import com.revature.entity.BankAccount;
import com.revature.entity.BankAccountBuilder;

public class AccountCreationMenu {

	public static int accountNumber = 0;
	private String customerId;
	private String password;
	private String firstName;
	private String lastName;
	private Account accountType;
	private BankAccount bankAccount;

	public AccountCreationMenu() {

		Scanner input = new Scanner(System.in);

		System.out.print("Please enter your first name: ");
		this.firstName = input.nextLine();

		System.out.println();

		System.out.print("Please enter your last name: ");
		this.lastName = input.nextLine();

		System.out.println();

		System.out.print("Please enter a unique username: ");
		this.customerId = input.nextLine();

		System.out.println();

		System.out.print("Please enter a password: ");
		this.password = input.nextLine();

		System.out.println();

		System.out.println("What type of account would you like to create?");
		System.out.println("\t1. Checking Account\n\t2. Savings Account\n\t3. Joint Account");

		String choice = input.nextLine();
		choice = choice.toLowerCase();

		boolean b = true;

		do {
			if (choice.equals("1") || choice.equals("checking") || choice.equals("checking account")) {
				this.accountType = Account.CHECKING;
				b = false;
			} else if (choice.equals("2") || choice.equals("savings") || choice.equals("savings account")) {
				this.accountType = Account.SAVINGS;
				b = false;
			} else if (choice.equals("3") || choice.equals("joint") || choice.equals("joint account")) {
				this.accountType = Account.JOINT;
				b = false;
			} else {
				System.out.print("Please enter a valid input: ");
				input = new Scanner(System.in);
				choice = input.nextLine();
				choice = choice.toLowerCase();
			}
		} while (b);

		System.out.println();

		input.close();

		this.bankAccount = new BankAccountBuilder().with($ -> {
			$.accountNumber = AccountCreationMenu.accountNumber;
			$.customerId = this.customerId;
			$.password = this.password;
			$.account = this.accountType;
		}).buildBankAccount();

		accountNumber++;

		CreateAccountDAO dao = new CreateAccountDAO();
		Connection connection = dao.getConnection();
		dao.setDBValues(connection, this.firstName, this.lastName, this.customerId, this.password);

		System.out.println("Application Creation Succesful! Please Check Back Later.");
	}

	public BankAccount getAccount() {
		return this.bankAccount;
	}
}
