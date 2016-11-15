package com.veniqs.controller.admin;

import java.sql.Connection;
import java.sql.ResultSet;
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

public class OnlyNameAddPane extends GridPane {

	private Label nameLabel;
	private TextField nameTextField;
	private Label messageLabel;

	private Button addButton;

	private ComboBox<String> whatToAddComboBox;

	private EventHandler<ActionEvent> handler;

	public OnlyNameAddPane(ComboBox<String> whatToAddComboBox) {
		this.whatToAddComboBox = whatToAddComboBox;

		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));

		nameLabel = new Label("Name");
		nameTextField = new TextField();
		messageLabel = new Label();

		addButton = new Button("Add to database");
		handler = new AddOneEntityHandler(whatToAddComboBox, nameTextField);

		addButton.setOnAction(handler);

		this.add(nameLabel, 1, 1);
		this.add(nameTextField, 2, 1);
		this.add(addButton, 2, 2);
		this.add(messageLabel, 1, 3);
	}

}

class AddOneEntityHandler implements EventHandler<ActionEvent> {

	private ComboBox<String> comboBox;
	private TextField nameTextField;

	AddOneEntityHandler(ComboBox<String> comboBox, TextField nameTextField) {
		this.comboBox = comboBox;
		this.nameTextField = nameTextField;
	}

	@Override
	public void handle(ActionEvent arg0) {
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			System.out.println("Opened database successfully AddOneEntityHandler");
			Statement stmt = c.createStatement();
			String query = whatToAdd();
			stmt.executeQuery(query);
			/*
			 * System.out.println(query); ResultSet rs =
			 * stmt.executeQuery(query); while (rs.next()) { int id =
			 * rs.getInt(rs.getMetaData().getColumnName(1));
			 * System.out.println(id + " " + rs.getMetaData().getColumnName(1));
			 * } rs.close();
			 */
			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ОШИБКА OnlyNamePane " + e.getClass().getName() + ": " + e.getMessage());
		}
	}

	private String whatToAdd() {
		String key = null;
		String query = null;
		key = comboBox.getValue();

		switch (key) {
		case "Publisher":
			query = "SELECT create_or_get_publisher_id('" + nameTextField.getText() + "');";
			break;
		case "Author":
			query = "SELECT create_or_get_author_id('" + nameTextField.getText() + "');";
			break;
		case "Language":
			query = "SELECT create_or_get_language_id('" + nameTextField.getText() + "');";
			break;
		case "Genre":
			query = "SELECT create_or_get_genre_id('" + nameTextField.getText() + "');";
			break;
		case "Customer":
			query = "SELECT create_or_get_customer_id('" + nameTextField.getText() + "');";
			break;
		}

		return query;
	}
}
