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
		
	}
	
	private boolean regularLoginCheck() {
		
		return false;
		}
	
	private boolean loginAsAdminCheck() {
		
		return false;
		}

	private void popError() {
		errorLabel.setText("Failed to sign in");
	}

}
