package com.revature.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.revature.entity.Account;
import com.revature.entity.BankAccount;
import com.revature.entity.BankAccountBuilder;

public class AccountCreationMenu {

	public static int accountNumber = 0;
	private String customerId;
	private String password;
	private Account accountType;
	private BankAccount bankAccount;
	private String folderLocation = "C:\\Users\\Derrick\\Documents\\Revature\\project-zero-DerrickCheah\\TextFiles\\";
	private File usernames = new File(folderLocation + "Usernames.txt");
	private File passwords = new File(folderLocation + "Passwords.txt");
	private File checkingAccount = new File(folderLocation + "CheckingAccounts.txt");
	private File savingsAccount = new File(folderLocation + "SavingsAccount.txt");
	private File jointAccount = new File(folderLocation + "JointAccounts.txt");

	public AccountCreationMenu() {
		try (PrintStream usernamesPS = new PrintStream(new FileOutputStream(usernames, true));
				PrintStream passwordsPS = new PrintStream(new FileOutputStream(passwords, true));
				PrintStream checkingPS = new PrintStream(new FileOutputStream(checkingAccount, true));
				PrintStream savingsPS = new PrintStream(new FileOutputStream(savingsAccount, true));
				PrintStream jointPS = new PrintStream(new FileOutputStream(jointAccount, true));) {

			Scanner input = new Scanner(System.in);
			System.out.print("Please enter a unique username: ");
			this.customerId = input.nextLine();
			usernamesPS.println(this.customerId);

			System.out.println();

			System.out.print("Please enter a password: ");
			this.password = input.nextLine();
			passwordsPS.println(this.password);

			System.out.println();

			System.out.println("What type of account would you like to create?");
			System.out.println("\t1. Checking Account\n\t2. Savings Account\n\t3. Joint Account");

			String choice = input.nextLine();
			choice = choice.toLowerCase();

			boolean b = true;

			do {
				if (choice.equals("1") || choice.equals("checking") || choice.equals("checking account")) {
					this.accountType = Account.CHECKING;
					checkingPS.println("1");
					savingsPS.println("0");
					jointPS.println("0");
					b = false;
				} else if (choice.equals("2") || choice.equals("savings") || choice.equals("savings account")) {
					this.accountType = Account.SAVINGS;
					checkingPS.println("0");
					savingsPS.println("1");
					jointPS.println("0");
					b = false;
				} else if (choice.equals("3") || choice.equals("joint") || choice.equals("joint account")) {
					this.accountType = Account.JOINT;
					checkingPS.println("0");
					savingsPS.println("0");
					jointPS.println("1");
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

			// TODO
			// Store account information as an arraylist.

			accountNumber++;

			System.out.println("Application Pending. Come back later.");
		} catch (FileNotFoundException e) {

		}
	}

	public BankAccount getAccount() {
		return this.bankAccount;
	}
}
