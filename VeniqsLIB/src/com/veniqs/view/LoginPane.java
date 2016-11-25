package com.veniqs.view;

import com.veniqs.controller.login.SignInButtonHandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPane extends Stage {

	public static final LoginPane INSTANCE = new LoginPane(); // singleton

	private BorderPane root;
	private Scene scene;

	private LoginPane() {
		root = new BorderPane();

		// username
		Label loginLabel = new Label("Username");
		TextField loginTextField = textFieldBuilder();
		VBox loginBox = new VBox(loginLabel, loginTextField);

		// password
		Label passwordLabel = new Label("Password");
		PasswordField passwordTextField = passwordFieldBuilder();
		VBox passwordBox = new VBox(passwordLabel, passwordTextField);

		// error
		Label errorLabel = new Label();
		errorLabel.getStyleClass().add("error");
		HBox errorBox = new HBox(errorLabel);
		
		//!TODO DELETE THIS SHIT!
		@Deprecated Button asAdmin = new Button("Admin");
		@Deprecated Button asLibrarian = new Button("Librarian");
		errorBox.getChildren().addAll(asAdmin, asLibrarian);
		asAdmin.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			@Deprecated public void handle(ActionEvent event) {
				// TODO DELETE THIS SHIT
				AdminPane.getInstance();
				//LoginPanel.INSTANCE.close();
			}
		});
		asLibrarian.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			@Deprecated public void handle(ActionEvent event) {
				// TODO DELETE THIS SHIT
				LibrarianPane.getInstance(null);
				//LoginPanel.INSTANCE.close();
			}
		});
		//!TODO DELETE THIS SHIT!
		
		// button
		Button signInButton = new Button("Sign in");
		signInButton.setPrefWidth(Double.POSITIVE_INFINITY);
		signInButton.getStyleClass().add("signin");
		SignInButtonHandler signInButtonHandler = new SignInButtonHandler(loginTextField, passwordTextField,
				errorLabel);
		signInButton.setOnAction(signInButtonHandler);

		// last preparing
		VBox signInBox = new VBox(loginBox, passwordBox, signInButton, errorBox);
		signInBox.setPadding(new Insets(20));
		signInBox.setSpacing(10);

		root.setCenter(signInBox);

		scene = new Scene(root, 200, 200);
		this.setResizable(false);
		this.setScene(scene);
		this.setTitle("Login panel");
		this.show();
	}

	public Scene getMyScene() {
		return scene;
	}

	private PasswordField passwordFieldBuilder() {
		PasswordField pf = new PasswordField();
		pf.setMinWidth(100);
		pf.setPrefWidth(Double.POSITIVE_INFINITY);
		return pf;
	}

	private TextField textFieldBuilder() {
		TextField tf = new TextField();
		tf.setMinWidth(100);
		tf.setPrefWidth(Double.POSITIVE_INFINITY);
		return tf;
	}

}
