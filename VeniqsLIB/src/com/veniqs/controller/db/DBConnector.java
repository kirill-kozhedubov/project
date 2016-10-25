package com.veniqs.controller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.veniqs.model.Database;

public class DBConnector {

	private Connection connection = null;

	public DBConnector(boolean autoCommitProperty) {
		connection = createConnection(autoCommitProperty);
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void setAutoCommit(boolean autoCommitProperty) {
		try {
			connection.setAutoCommit(autoCommitProperty);
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
	}

	private Connection createConnection(boolean setAutoCommit) {
		Connection dbConnection = null;
		try {

			Class.forName("org.postgresql.Driver");
			dbConnection = DriverManager.getConnection(Database.getPath(), Database.getUsername(),
					Database.getPassword());
			dbConnection.setAutoCommit(setAutoCommit);
			System.out.println("Opened database successfully");

			// c.close(); !TODO I GOTTA CLOSE CONNECTION FROM TIMES TO TIMES
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		System.out.println("connection opened");
		return dbConnection;
	}

}
