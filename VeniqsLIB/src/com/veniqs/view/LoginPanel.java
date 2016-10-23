package com.veniqs.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginPanel extends Stage {

	public static final LoginPanel INSTANCE = new LoginPanel(); //singleton
	
	private BorderPane root;
	private Scene scene;
	
	private LoginPanel() {
		root = new BorderPane();
		scene = new Scene(root, 400, 400);
		
		this.setScene(scene);
		this.setTitle("Login panel");
		this.show();	
	}
	
	public BorderPane getRoot() {
		return root;
	}
	
	public Scene getMyScene() {
		return scene;
	}
}
