package com.revature.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAccountDAO {

	private final String url = "jdbc:postgresql://projectzerodb.cshthkt1n48j.us-east-2.rds.amazonaws.com:5432/ProjectZeroDB";
	private final String user = "derrick_cheah";
	private final String pass = "c98woD9hoMGq";

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setDBValues(Connection connection, String firstName, String lastName, String username,
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

}
