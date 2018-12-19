package com.revature.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnection {

	private final String url = "jdbc:postgresql://projectzerodb.cshthkt1n48j.us-east-2.rds.amazonaws.com:5432/ProjectZeroDB";
	private final String user = "derrick_cheah";
	private final String pass = "c98woD9hoMGq";
	private Connection connection;

	public Connection getConnection() {
		try {
			if (connection == null) {
				return DriverManager.getConnection(url, user, pass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
