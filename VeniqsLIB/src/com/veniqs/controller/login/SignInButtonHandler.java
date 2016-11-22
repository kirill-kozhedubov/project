package com.veniqs.controller.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.veniqs.controller.db.DBConnector;
import com.veniqs.model.Librarian;
import com.veniqs.view.AdminPane;
import com.veniqs.view.LibrarianPane;
import com.veniqs.view.LoginPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInButtonHandler implements EventHandler<ActionEvent> {

	boolean logOnFlag;
	boolean logOnAsAdminFlag;

	private TextField loginTextField;
	private PasswordField passwordTextField;
	private Label errorLabel;

	public SignInButtonHandler(TextField loginTextField, PasswordField passwordTextField, Label errorLabel) {
		this.loginTextField = loginTextField;
		this.passwordTextField = passwordTextField;
		this.errorLabel = errorLabel;

	}

	@Override
	public void handle(ActionEvent arg0) {
		String username = loginTextField.getText();
		String password = passwordTextField.getText();
		getLoginStatus(username, password);
	}

	private void getLoginStatus(String username, String password) {
		Librarian lib = regularLoginCheck(username, password);
		if (lib != null) {
				AdminPane.getInstance();
				LoginPane.INSTANCE.close();
		} else {
			popError();
		}
	}

	private Librarian regularLoginCheck(String username, String password) {
		Librarian lib = null;
		try {
			Class.forName("org.postgresql.Driver");
			DBConnector dbcon = new DBConnector(false);
			Connection c = dbcon.getConnection();
			System.out.println("Opened database successfully");
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM LIBRARIAN WHERE LOGIN='" + username + "' AND PASSWORD='" + password + "' LIMIT 1");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("full_name");
				String login = rs.getString("login");
				String pass = rs.getString("password");
				lib = new Librarian(id, name, login, pass);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println("ОШЫБКА С БИБЛИОТЕКАРЕМ " + e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		// System.out.println("Operation done successfully");
		return lib;
	}

	private void popError() {
		errorLabel.setText("Failed to sign in");
	}

}
