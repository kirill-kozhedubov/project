package com.veniqs.model;

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

	public String getLogin() {
		return login;
	}

	@Override
	public String toString() {
		return "Librarian [id=" + id + ", fullName=" + fullName + ", login=" + login + ", password=" + password + "]";
	}

}
