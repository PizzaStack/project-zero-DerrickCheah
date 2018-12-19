package com.revature.service;

import java.sql.Connection;
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

	public void setCheckingDBValues(Connection connection, String username, int accountNumber) {
		try {
			Statement statement = connection.createStatement();
			String insert = "insert into checkingaccount ";
			String values = String.format("values ('%f', '%d', '%s', '%s')", 0.00, accountNumber, username, "Pending");
			statement.executeUpdate(insert + values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setSavingsDBValues(Connection connection, String username, int accountNumber) {
		try {
			Statement statement = connection.createStatement();
			String insert = "insert into savingsaccount ";
			String values = String.format("values ('%f', '%d', '%s', '%s')", 0.00, accountNumber, username, "Pending");
			statement.executeUpdate(insert + values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setJointDBValues(Connection connection, String username, String username2, int accountNumber) {
		try {
			Statement statement = connection.createStatement();
			String insert = "insert into jointaccount ";
			String values = String.format("values ('%f', '%d', '%s', '%s', '%s')", 0.00, accountNumber, username,
					username2, "Pending");
			statement.executeUpdate(insert + values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
