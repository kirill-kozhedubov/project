package com.veniqs.controller.admin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PublisherPane extends VBox {

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
		
		nameLabel = new Label("Name");
		nameTextField = new TextField();
		messageLabel = new Label();
		addingInfoBox = new HBox(nameLabel, nameTextField, messageLabel);
		addingInfoBox.setPadding(new Insets(5));

		addButton = new Button("Add");
		checkButton = new Button("Check");
		buttonBox = new HBox(addButton, checkButton);
		buttonBox.setPadding(new Insets(5));

		AddPublisherHandler addHandler = new AddPublisherHandler(whatToAddComboBox);
		CheckPublisherHandler checkHandler = new CheckPublisherHandler(whatToAddComboBox);
		addButton.setOnAction(addHandler);
		checkButton.setOnAction(checkHandler);
		
		
		
		
		this.setPadding(new Insets(5));
		this.getChildren().addAll(addingInfoBox, buttonBox);
	}

}

class AddPublisherHandler implements EventHandler<ActionEvent> {

	private ComboBox<String> comboBox;

	AddPublisherHandler(ComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}

class CheckPublisherHandler implements EventHandler<ActionEvent> {
	
	private ComboBox<String> comboBox;

	CheckPublisherHandler(ComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

}
