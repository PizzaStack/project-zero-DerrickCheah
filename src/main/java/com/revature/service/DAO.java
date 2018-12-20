package com.revature.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

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

	public void setCheckingDBValues(Connection connection, String username, int accountNumber, String accountstatus) {
		try {
			Statement statement = connection.createStatement();
			String insert = "insert into checkingaccount ";
			String values = String.format("values ('%f', '%d', '%s', '%s')", 0.00, accountNumber, username, accountstatus);
			statement.executeUpdate(insert + values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		AccountCreationMenu.accountNumber++;
	}

	public void setSavingsDBValues(Connection connection, String username, int accountNumber, String accountstatus) {
		try {
			Statement statement = connection.createStatement();
			String insert = "insert into savingsaccount ";
			String values = String.format("values ('%f', '%d', '%s', '%s')", 0.00, accountNumber, username, accountstatus);
			statement.executeUpdate(insert + values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		AccountCreationMenu.accountNumber++;
	}

	public void setJointDBValues(Connection connection, String username, String username2, int accountNumber, String accountstatus) {
		try {
			Statement statement = connection.createStatement();
			String insert = "insert into jointaccount ";
			String values = String.format("values ('%f', '%d', '%s', '%s', '%s')", 0.00, accountNumber, username,
					username2, accountstatus);
			statement.executeUpdate(insert + values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		AccountCreationMenu.accountNumber++;
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
				} else {
					System.out.println("Checking Account Number: " + rs.getInt(7));
					System.out.printf("Checking Account Balance: $%.2f\n", rs.getDouble(6));
				}
				
				System.out.println();
				
				if (rs.getInt(11) == 0 || rs.getString(13).equals("Not Active")) {
					System.out.println("Savings Account: N/A");
				} else {
					System.out.println("Savings Account Number: " + rs.getInt(11));
					System.out.printf("Savings Account Balance: $%.2f\n", rs.getDouble(10));
				}
				
				System.out.println();
				
				if (rs.getInt(15) == 0 || rs.getString(18).equals("Not Active")) {
					System.out.println("Joint Account: N/A");
				} else {
					System.out.println("Joint Account Number: " + rs.getInt(15));
					System.out.printf("Joint Account Balance: $%.2f\n", rs.getDouble(14));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
