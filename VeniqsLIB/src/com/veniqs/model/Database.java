package com.veniqs.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Database implements Serializable {

	private String password;
	private String username;
	private String path;

	private Database(String password, String username, String path) {
		this.password = password;
		this.username = username;
		this.path = path;
	}

	private void setDatabaseUp() {
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream("database.out");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(this);
			objectOutputStream.flush();
			objectOutputStream.close();
		} catch (IOException e) {
			System.out.println("can't create db in file");
			e.printStackTrace();
		} //

	}

	private static Database getDB() {
		Database db = null;
		try {
			FileInputStream fileInputStream = new FileInputStream("database.out");
			if (fileInputStream.available() > 0) {
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				db = (Database) objectInputStream.readObject();

			} else if (fileInputStream.available() <= 0) {
				throw new Exception("db can't load from file");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("can't laod db");
		}
		return db;
	}

	public static String getUsername() {
		Database db = Database.getDB();
		return db.username;
	}

	public static String getPassword() {
		Database db = Database.getDB();
		return db.password;
	}

	public static String getPath() {
		Database db = Database.getDB();
		return db.path;
	}

	public static void main(String[] args) {

	}
}
