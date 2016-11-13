package com.veniqs.controller.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.veniqs.controller.db.DBConnector;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PublisherPane extends GridPane {

	private Label nameLabel;
	private TextField nameTextField;
	private Label messageLabel;

	private Button addButton;
	private Button checkButton;

	private HBox addingInfoBox;
	private HBox buttonBox;

	private ComboBox<String> whatToAddComboBox;
	
	public PublisherPane(ComboBox<String> whatToAddComboBox) {
		this.whatToAddComboBox = whatToAddComboBox;
	    this.setHgap(10);
	    this.setVgap(10);
	    this.setPadding(new Insets(10, 10, 10, 10));
	    
		nameLabel = new Label("Name");
		nameTextField = new TextField();
		messageLabel = new Label();

		addButton = new Button("Add to database");

		AddPublisherHandler addHandler = new AddPublisherHandler(whatToAddComboBox);
		addButton.setOnAction(addHandler);

		
		
		
		this.add(nameLabel, 1, 1);
		this.add(nameTextField, 2, 1);
		this.add(addButton, 2, 2);
		this.add(messageLabel, 1, 3);
	}

}

class AddPublisherHandler implements EventHandler<ActionEvent> {

	private ComboBox<String> comboBox;

	AddPublisherHandler(ComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	@Override
	public void handle(ActionEvent arg0) {
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			System.out.println("Opened database successfully addpubleventHandler");
			Statement stmt = c.createStatement();
			
			
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

