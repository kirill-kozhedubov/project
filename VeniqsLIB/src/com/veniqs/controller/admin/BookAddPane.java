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

public class BookAddPane extends GridPane {

	private Label nameLabel;
	private TextField nameTextField;

	private Button addButton;

	private ComboBox<String> whatToAddComboBox;

	private EventHandler<ActionEvent> handler;

	public BookAddPane() {
		this.whatToAddComboBox = whatToAddComboBox;

		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));

		nameLabel = new Label("Full name");
		nameTextField = new TextField();
		
		
		addButton = new Button("Add to database");
		handler = new AddBookHandler(whatToAddComboBox);

		addButton.setOnAction(handler);
		
		// this.add(child, col, row);
	}

}

class AddBookHandler implements EventHandler<ActionEvent> {

	private ComboBox<String> comboBox;
	
	
	AddBookHandler(ComboBox<String> comboBox) {
		this.comboBox = comboBox;
		
	}

	@Override
	public void handle(ActionEvent arg0) {
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			System.out.println("Opened database successfully LibrarianAddPane");
			Statement stmt = c.createStatement();
			//String query = "SELECT create_or_get_librarian_id('" + name.getText() + "', '" + login.getText() + "', '" + password.getText() + "');";
			//stmt.executeQuery(query);
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
			System.out.println("ОШИБКА " + e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
