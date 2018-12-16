package com.revature;

import java.util.Scanner;

import com.revature.service.AccountCreationMenu;

public class App {
	public static void main(String[] args) {
		System.out.println("Welcome to Derrick's Bank! Please select an option to begin.");
		System.out.println("\t1. Apply for a new account\n\t2. Customer log in\n\t3. Employee log in");
		
		Scanner input = new Scanner(System.in);
		
		if(input.nextInt() == 1) {
			new AccountCreationMenu();
		}
		
		input.close();
	}
}
