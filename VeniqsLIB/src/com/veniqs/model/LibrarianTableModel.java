package com.veniqs.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LibrarianTableModel {
	private SimpleIntegerProperty id;
	private SimpleStringProperty fullName;
	private SimpleStringProperty login;
	private SimpleStringProperty password;

	public LibrarianTableModel(int idC, String fullNameC, String loginC, String passwordC) {
		id = new SimpleIntegerProperty(idC);
		fullName = new SimpleStringProperty(fullNameC);
		login = new SimpleStringProperty(loginC);
		password = new SimpleStringProperty(passwordC);
	}

	public int getId() {
		return id.get();
	}

	public String getFullName() {
		return fullName.get();
	}

	public String getLogin() {
		return login.get();
	}

	public String getPassword() {
		return password.get();
	}
	
	
}
