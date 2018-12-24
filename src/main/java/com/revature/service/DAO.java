package com.revature.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.entity.BankAccount;
import com.revature.entity.BankAccountBuilder;

public class DAO {

	double balance;
	int count;

	public void setCustomerDBValues(Connection connection, String firstName, String lastName, String username,
			String password) {
		try {
			Statement statement = connection.createStatement();
			String insert = "insert into customer ";
			String values = String.format("values ('%s', '%s', '%s', '%s', %b)", firstName, lastName, username,
					password, true);
			statement.executeUpdate(insert + values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setCheckingDBValues(Connection connection, String username, String accountstatus) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into counter values(default)");
			ResultSet rs = statement.executeQuery("select * from counter");
			while (rs.next()) {
				count = rs.getInt(1);
			}
			String insert = "insert into checkingaccount ";
			String values = String.format("values ('%f', '%d', '%s', '%s')", 0.00, count, username, accountstatus);
			statement.executeUpdate(insert + values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setSavingsDBValues(Connection connection, String username, String accountstatus) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into counter values(default)");
			ResultSet rs = statement.executeQuery("select * from counter");
			while (rs.next()) {
				count = rs.getInt(1);
			}
			String insert = "insert into savingsaccount ";
			String values = String.format("values ('%f', '%d', '%s', '%s')", 0.00, count, username, accountstatus);
			statement.executeUpdate(insert + values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setJointDBValues(Connection connection, String username, String username2, String accountstatus) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into counter values(default)");
			ResultSet rs = statement.executeQuery("select * from counter");
			while (rs.next()) {
				count = rs.getInt(1);
			}
			String insert = "insert into jointaccount ";
			String values = String.format("values ('%f', '%d', '%s', '%s', '%s')", 0.00, count, username, username2,
					accountstatus);
			statement.executeUpdate(insert + values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkCustomerLogin(Connection connection, String username, String password) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(
					String.format("select username, password from customer where username = '%s'", username));
			while (rs.next()) {
				if (rs.getString(2).equals(password)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkEmployeeLogin(Connection connection, String username, String password) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(
					String.format("select username, password from employee where username = '%s'", username));
			while (rs.next()) {
				if (rs.getString(2).equals(password)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void getAccountInformation(Connection connection, String username) {
		try {
			Statement statement = connection.createStatement();
			String select = "select * from customer ";
			String join1 = "left outer join checkingaccount on customer.username = checkingaccount.username ";
			String join2 = "left outer join savingsaccount on customer.username = savingsaccount.username ";
			String join3 = "left outer join jointaccount on customer.username = jointaccount.username ";
			String condition = String.format("where customer.username = '%s'", username);
			ResultSet rs = statement.executeQuery(select + join1 + join2 + join3 + condition);
			while (rs.next()) {
				System.out.println("Name: " + rs.getString(1) + " " + rs.getString(2));
				System.out.println("Username: " + rs.getString(3));
				System.out.println();

				if (rs.getInt(7) == 0 || rs.getString(9).equals("Not Active")) {
					System.out.println("Checking Account: N/A");
				} else if (rs.getString(9).equals("Pending")) {
					System.out.println("Checking Account: Pending");
				} else {
					System.out.println("Checking Account Number: " + rs.getInt(7));
					System.out.printf("Checking Account Balance: $%.2f\n", rs.getDouble(6));
				}

				System.out.println();

				if (rs.getInt(11) == 0 || rs.getString(13).equals("Not Active")) {
					System.out.println("Savings Account: N/A");
				} else if (rs.getString(13).equals("Pending")) {
					System.out.println("Savings Account: Pending");
				} else {
					System.out.println("Savings Account Number: " + rs.getInt(11));
					System.out.printf("Savings Account Balance: $%.2f\n", rs.getDouble(10));
				}

				System.out.println();

				if (rs.getInt(15) == 0 || rs.getString(18).equals("Not Active")) {
					System.out.println("Joint Account: N/A");
				} else if (rs.getString(18).equals("Pending")) {

				} else {
					System.out.println("Joint Account Number: " + rs.getInt(15));
					System.out.printf("Joint Account Balance: $%.2f\n", rs.getDouble(14));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkUniqueUsername(Connection connection, String username) {
		try {
			Statement statement = connection.createStatement();
			String sql = String.format("select username from customer where username = '%s'", username);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString(1).equals(username)) {
					System.out.println();
					System.out.println("Username already in use!");
					System.out.println();
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void depositFunds(Connection connection, String username, double amount, String accountChoice,
			Scanner input) {
		if (accountChoice.contains("checking")) {
			accountChoice = "checkingaccount";
		} else if (accountChoice.contains("savings")) {
			accountChoice = "savingsaccount";
		} else if (accountChoice.contains("joint")) {
			accountChoice = "jointaccount";
		}
		try {
			Statement statement = connection.createStatement();
			String sql = String.format("select * from %s where username = '%s'", accountChoice, username);
			ResultSet rs;
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				balance = rs.getDouble(1);
			}

			BankAccount ba = new BankAccountBuilder().with($ -> {
				$.balance = balance;
				$.username = username;
			}).buildBankAccount();

			boolean z = ba.deposit(amount);
			while (z) {
				z = ba.deposit(input.nextDouble());
			}
			balance = ba.getBalance();

			String update = String.format("update %s set balance = %.2f where username = '%s'", accountChoice, balance,
					username);
			statement.executeUpdate(update);

			System.out.println("Deposit Successful!");
			System.out.printf("New balance = %.2f\n", balance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getActiveAccounts(Connection connection, String username) {
		String result = "";
		try {
			Statement statement = connection.createStatement();
			String select1 = String.format("select accountstatus from checkingaccount where username = '%s'", username);
			ResultSet rs1 = statement.executeQuery(select1);
			while (rs1.next()) {
				if (rs1.getString(1).equals("Active")) {
					result += "Checking ";
				}
			}
			String select2 = String.format("select accountstatus from savingsaccount where username = '%s'", username);
			ResultSet rs2 = statement.executeQuery(select2);
			while (rs2.next()) {
				if (rs2.getString(1).equals("Active")) {
					result += "Savings ";
				}
			}
			String select3 = String.format("select accountstatus from jointaccount where username = '%s'", username);
			ResultSet rs3 = statement.executeQuery(select3);
			while (rs3.next()) {
				if (rs3.getString(1).equals("Active")) {
					result += "Joint";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void withdrawFunds(Connection connection, String username, double amount, String accountChoice,
			Scanner input) {
		if (accountChoice.contains("checking")) {
			accountChoice = "checkingaccount";
		} else if (accountChoice.contains("savings")) {
			accountChoice = "savingsaccount";
		} else if (accountChoice.contains("joint")) {
			accountChoice = "jointaccount";
		}
		try {
			Statement statement = connection.createStatement();
			String sql = String.format("select * from %s where username = '%s'", accountChoice, username);
			ResultSet rs;
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				balance = rs.getDouble(1);
			}

			BankAccount ba = new BankAccountBuilder().with($ -> {
				$.balance = balance;
				$.username = username;
			}).buildBankAccount();

			boolean z = ba.withdraw(amount);
			while (z) {
				z = ba.withdraw(input.nextDouble());
				System.out.println();
			}
			balance = ba.getBalance();
			System.out.println();

			String update = String.format("update %s set balance = %.2f where username = '%s'", accountChoice, balance,
					username);
			statement.executeUpdate(update);

			System.out.println("Withdraw Successful!");
			System.out.printf("New balance = %.2f\n", balance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//
//	public boolean exists(Connection connection, int accountNumber) {
//		try {
//			Statement statement = connection.createStatement();
//			String sql = String.format("select accountnumber from customer where username = '%s'", username);
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				if (rs.getString(1).equals(username)) {
//					System.out.println();
//					System.out.println("Username already in use!");
//					System.out.println();
//					return false;
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return false;
//	}
}
