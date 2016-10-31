package com.veniqs.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.veniqs.controller.db.DBConnector;

public class Librarian {

	private int id;
	private String fullName;
	private String login;
	private String password;
	
	public Librarian(int id, String fullName, String login, String password) {
		this.id = id;
		this.fullName = fullName;
		this.login = login;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Librarian [id=" + id + ", fullName=" + fullName + ", login=" + login + ", password=" + password + "]";
	}

	public static void main(String args[]) {
		Connection c = null;
		Statement stmt = null;
		List<Librarian> libList = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
			DBConnector dbcon = new DBConnector(false);
			c =  dbcon.getConnection();
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String loginn = "test_librarian";
			String passwordd = "librarian";
			ResultSet rs = stmt.executeQuery("SELECT * FROM LIBRARIAN "
					+ "WHERE LOGIN='" + loginn + "' AND PASSWORD='" + passwordd + "';");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("full_name");
				String login = rs.getString("login");
				String password = rs.getString("password");
				Librarian lib = new Librarian(id, name, login, password);
				libList.add(lib);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println("ОШЫБКА" + e.getClass().getName() + ": " + e.getMessage());
			//System.exit(0);
		}
		System.out.println("Operation done successfully");
		for (Librarian librarian : libList) {
			System.out.println(librarian);
		}
	}
	
	
	
	
}
