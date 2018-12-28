package com.revature.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.entity.BankAccount;
import com.revature.entity.BankAccountBuilder;

public class DAO {

	String username;
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
			String join3 = "left outer join jointaccount on customer.username = jointaccount.username or customer.username = jointaccount.username2 ";
			String condition = String.format("where customer.username = '%s'", username);
			ResultSet rs = statement.executeQuery(select + join1 + join2 + join3 + condition);
			while (rs.next()) {
				System.out.println("Name: " + rs.getString(1) + " " + rs.getString(2));
				System.out.println("Username: " + rs.getString(3));
				System.out.println();

				if (rs.getInt(6) == 0 || rs.getString(8).equals("Not Active")) {
					System.out.println("Checking Account: N/A");
				} else if (rs.getString(8).equals("Pending")) {
					System.out.println("Checking Account: Pending");
				} else {
					System.out.println("Checking Account Number: " + rs.getInt(6));
					System.out.printf("Checking Account Balance: $%.2f\n", rs.getDouble(5));
				}

				System.out.println();

				if (rs.getInt(10) == 0 || rs.getString(12).equals("Not Active")) {
					System.out.println("Savings Account: N/A");
				} else if (rs.getString(12).equals("Pending")) {
					System.out.println("Savings Account: Pending");
				} else {
					System.out.println("Savings Account Number: " + rs.getInt(10));
					System.out.printf("Savings Account Balance: $%.2f\n", rs.getDouble(9));
				}

				System.out.println();

				if (rs.getInt(14) == 0 || rs.getString(17).equals("Not Active")) {
					System.out.println("Joint Account: N/A");
				} else if (rs.getString(17).equals("Pending")) {
					System.out.println("Joint Account: Pending");
				} else {
					System.out.println("Joint Account Number: " + rs.getInt(14));
					System.out.printf("Joint Account Balance: $%.2f\n", rs.getDouble(13));
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
			String sql = "";
			if (accountChoice.equals("jointaccount")) {
				sql = String.format("select * from %s where username = '%s' or username2 = '%s'", accountChoice,
						username, username);
			} else {
				sql = String.format("select * from %s where username = '%s'", accountChoice, username);
			}
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

			String update = "";
			if (accountChoice.equals("jointaccount")) {
				update = String.format("update %s set balance = %.2f where username = '%s' or username2 = '%s'",
						accountChoice, this.balance, username, username);
			} else {
				update = String.format("update %s set balance = %.2f where username = '%s'", accountChoice,
						this.balance, username);
			}
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
			String select3 = String.format(
					"select accountstatus from jointaccount where username = '%s' or username2 = '%s'", username,
					username);
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
			String sql = "";
			if (accountChoice.equals("jointaccount")) {
				sql = String.format("select * from %s where username = '%s' or username2 = '%s'", accountChoice,
						username, username);
			} else {
				sql = String.format("select * from %s where username = '%s'", accountChoice, username);
			}
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				this.balance = rs.getDouble(1);
			}

			BankAccount ba = new BankAccountBuilder().with($ -> {
				$.balance = this.balance;
				$.username = username;
			}).buildBankAccount();

			boolean z = ba.withdraw(amount);
			while (z) {
				z = ba.withdraw(input.nextDouble());
				System.out.println();
			}
			this.balance = ba.getBalance();

			String update = "";
			if (accountChoice.equals("jointaccount")) {
				update = String.format("update %s set balance = %.2f where username = '%s' or username2 = '%s'",
						accountChoice, this.balance, username, username);
			} else {
				update = String.format("update %s set balance = %.2f where username = '%s'", accountChoice,
						this.balance, username);
			}
			statement.executeUpdate(update);

			System.out.println("Withdraw Successful!");
			System.out.printf("New balance = %.2f\n", this.balance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean exists(Connection connection, int accountNumber) {
		try {
			Statement statement = connection.createStatement();
			String select = "select * from checkingaccount ";
			String join1 = "left outer join savingsaccount on checkingaccount.username = savingsaccount.username ";
			String join2 = "left outer join jointaccount on checkingaccount.username = jointaccount.username or checkingaccount.username = jointaccount.username2";
			ResultSet rs = statement.executeQuery(select + join1 + join2);
			while (rs.next()) {
				if (rs.getInt(2) == accountNumber) {
					if (rs.getString(4).equals("Active")) {
						return true;
					} else {
						System.out.println("Account not active.");
						return false;
					}
				} else if (rs.getInt(6) == accountNumber) {
					if (rs.getString(8).equals("Active")) {
						return true;
					} else {
						System.out.println("Account not active.");
						return false;
					}
				} else if (rs.getInt(10) == accountNumber) {
					if (rs.getString(13).equals("Active")) {
						return true;
					} else {
						System.out.println("Account not active.");
						return false;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void transferFunds(Connection connection, String username, double amount, String accountChoice,
			int otherAccount, Scanner input) {
		if (accountChoice.contains("checking")) {
			accountChoice = "checkingaccount";
		} else if (accountChoice.contains("savings")) {
			accountChoice = "savingsaccount";
		} else if (accountChoice.contains("joint")) {
			accountChoice = "jointaccount";
		}
		try {
			Statement statement = connection.createStatement();
			String sql = "";
			if (accountChoice.equals("jointaccount")) {
				sql = String.format("select * from %s where username = '%s' or username2 = '%s'", accountChoice,
						username, username);
			} else {
				sql = String.format("select * from %s where username = '%s'", accountChoice, username);
			}
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				this.balance = rs.getDouble(1);
			}

			BankAccount ba = new BankAccountBuilder().with($ -> {
				$.balance = this.balance;
				$.username = username;
			}).buildBankAccount();

			String sql1 = String.format("select balance, username from checkingaccount where accountnumber = '%d'",
					otherAccount);
			String sql2 = String.format(
					" union select balance, username from savingsaccount where accountnumber = '%d'", otherAccount);
			String sql3 = String.format(" union select balance, username from jointaccount where accountnumber = '%d'",
					otherAccount);
			rs = statement.executeQuery(sql1 + sql2 + sql3);
			while (rs.next()) {
				this.balance = rs.getDouble(1);
				this.username = rs.getString(2);
			}

			BankAccount otherBa = new BankAccountBuilder().with($ -> {
				$.balance = this.balance;
				$.username = this.username;
			}).buildBankAccount();

			boolean z = ba.transfer(otherBa, amount);
			while (z) {
				z = ba.transfer(otherBa, input.nextDouble());
				System.out.println();
			}

			this.balance = otherBa.getBalance();

			String update = String.format("update checkingaccount set balance = %.2f where accountnumber = '%d'",
					this.balance, otherAccount);
			statement.executeUpdate(update);

			update = String.format("update savingsaccount set balance = %.2f where accountnumber = '%d'", this.balance,
					otherAccount);
			statement.executeUpdate(update);

			update = String.format("update jointaccount set balance = %.2f where accountnumber = '%d'", this.balance,
					otherAccount);
			statement.executeUpdate(update);

			this.balance = ba.getBalance();

			if (accountChoice.equals("jointaccount")) {
				update = String.format("update %s set balance = %.2f where username = '%s' or username2 = '%s'",
						accountChoice, this.balance, username, username);
			} else {
				update = String.format("update %s set balance = %.2f where username = '%s'", accountChoice,
						this.balance, username);
			}

			statement.executeUpdate(update);

			System.out.println("Transfer Successful!");
			System.out.printf("New balance = %.2f\n", this.balance);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUsername(Connection connection, int accountNumber) {
		try {
			Statement statement = connection.createStatement();
			String sql1 = String.format("select username from checkingaccount where accountnumber = '%d'",
					accountNumber);
			String sql2 = String.format(" union select username from savingsaccount where accountnumber = '%d'",
					accountNumber);
			String sql3 = String.format(" union select username from jointaccount where accountnumber = '%d'",
					accountNumber);
			ResultSet rs = statement.executeQuery(sql1 + sql2 + sql3);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean isAdmin(Connection connection, String username) {
		try {
			Statement statement = connection.createStatement();
			String sql = String.format("select admin from employee where username = '%s'", username);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				return rs.getBoolean(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public String getAccountType(Connection connection, int accountNumber) {
		try {
			Statement statement = connection.createStatement();
			String sql1 = String.format("select accountnumber from checkingaccount where accountnumber = '%d'",
					accountNumber);
			ResultSet rs = statement.executeQuery(sql1);
			while (rs.next()) {
				if (rs.getInt(1) == accountNumber) {
					return "checkingaccount";
				}
			}
			String sql2 = String.format("select accountnumber from savingsaccount where accountnumber = '%d'",
					accountNumber);
			rs = statement.executeQuery(sql2);
			while (rs.next()) {
				if (rs.getInt(1) == accountNumber) {
					return "savingsaccount";
				}
			}
			String sql3 = String.format("select accountnumber from jointaccount where accountnumber = '%d'",
					accountNumber);
			rs = statement.executeQuery(sql3);
			while (rs.next()) {
				if (rs.getInt(1) == accountNumber) {
					return "jointaccount";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void getPending(Connection connection) {
		int count = 0;
		try {
			Statement statement = connection.createStatement();
			String sql = "select accountnumber, accountstatus from checkingaccount";
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("Pending Checking Accounts");
			while (rs.next()) {
				if (rs.getString(2).equals("Pending")) {
					System.out.println("\t" + rs.getInt(1));
					count++;
				}
			}

			if (count == 0) {
				System.out.println("\tNo Pending Accounts");
			}

			count = 0;
			sql = "select accountnumber, accountstatus from savingsaccount";
			rs = statement.executeQuery(sql);
			System.out.println("Pending Savings Accounts");
			while (rs.next()) {
				if (rs.getString(2).equals("Pending")) {
					System.out.println("\t" + rs.getInt(1));
					count++;
				}
			}

			if (count == 0) {
				System.out.println("\tNo Pending Accounts");
			}

			count = 0;
			sql = "select accountnumber, accountstatus from jointaccount";
			rs = statement.executeQuery(sql);
			System.out.println("Pending Joint Accounts");
			while (rs.next()) {
				if (rs.getString(2).equals("Pending")) {
					System.out.println("\t" + rs.getInt(1));
					count++;
				}
			}

			if (count == 0) {
				System.out.println("\tNo Pending Accounts");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void approveOrDeny(Connection connection, int accountNumber, String approveOrDeny) {
		String accountStatus = "";
		if (approveOrDeny.contains("approve")) {
			accountStatus = "Active";
		} else if (approveOrDeny.contains("deny")) {
			accountStatus = "Not Active";
		}

		try {
			Statement statement = connection.createStatement();
			String accountType = getAccountType(connection, accountNumber);
			String sql = String.format("update %s set accountstatus = '%s' where accountnumber = '%d'", accountType,
					accountStatus, accountNumber);
			statement.executeUpdate(sql);
			System.out.println("Update Successful!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cancelAccount(Connection connection, String customerUsername, String accountType, Scanner input) {
		System.out.print("Confirm cancellation: ");
		String confirm = input.next();
		confirm = confirm.toLowerCase();
		if (!confirm.contains("confirm")) {
			return;
		}
		try {
			Statement statement = connection.createStatement();
			String sql = "";
			if (accountType.equals("jointaccount")) {
				sql = String.format(
						"update %s set accountstatus = 'Not Active', balance = '%.2f' where username = '%s' or username2 = '%s'",
						accountType, 0.00, customerUsername, customerUsername);
			} else {
				sql = String.format("update %s set accountstatus = 'Not Active', balance = '%.2f' where username = '%s'",
						accountType, 0.00, customerUsername);
			}
			statement.executeUpdate(sql);
			System.out.println();
			System.out.println("Cancel Success!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void apply(Connection connection, String username, String accountChoice) {
		if (accountChoice.contains("checking")) {
			accountChoice = "checkingaccount";
		} else if (accountChoice.contains("savings")) {
			accountChoice = "savingsaccount";
		}
		try {
			Statement statement = connection.createStatement();
			String sql = String.format("update %s set accountstatus = 'Pending' where username = '%s'", accountChoice,
					username);
			statement.executeUpdate(sql);
			System.out.println("Application Successful! Check back later.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
