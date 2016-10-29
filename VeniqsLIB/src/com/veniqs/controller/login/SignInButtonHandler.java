package com.veniqs.controller.login;

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
		if (regularLoginCheck(username, password)) {
			if (loginAsAdminCheck(username, password)) {
				System.out.println("login as admin successful");
			}
			else {
				System.out.println("login as user successful");
			}
		}
		else {
			popError();
		}
	}
	
	private boolean regularLoginCheck(String username, String password) {
		
		return false;
		}
	
	private boolean loginAsAdminCheck(String username, String password) {
		// SELECT PASSWORD FROM LIBRARIANS WHERE LOGIN=ADMIN
		// AND CHECK IF PASSWORD FOR ADMIN IS RIGHT
		return false;
		}

	private void popError() {
		errorLabel.setText("Failed to sign in");
	}

}
