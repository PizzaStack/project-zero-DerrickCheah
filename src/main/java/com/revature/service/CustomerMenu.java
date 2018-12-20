package com.revature.service;

import java.sql.Connection;
import java.util.Scanner;

public class CustomerMenu {
	
	public CustomerMenu(Scanner input, Connection connection) {
		boolean loop = true;
		while(loop) {
			System.out.println("What would you like to do?");
			System.out.println("\t1. View Account Information"
					+ "\n\t2. Edit Account Information"
					+ "\n\t3. Withdraw Money"
					+ "\n\t4. Deposit Money"
					+ "\n\t5. Transfer Money"
					+ "\n\t6. Return to Main Menu");	
		}
	}

}
