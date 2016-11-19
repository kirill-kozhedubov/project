package com.veniqs.controller.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

	private Label titleLabel;
	private TextField titleTextField;

	private Label languageLabel;
	private TextField languageTextField;

	private Label publisherLabel;
	private TextField publisherTextField;

	private Label yearLabel;
	private TextField yearTextField;

	private Label genreLabel;
	private List<TextField> genreTextFields;
	private Button addAnotherGenreTF;
	
	private Label authorLabel;
	private List<TextField> authorTextFields;
	private Button addAnotherAuthorTF;

	private Button addButton;

	private ComboBox<String> whatToAddComboBox;

	private EventHandler<ActionEvent> handler;

	public BookAddPane(ComboBox<String> whatToAddComboBox) {
		this.whatToAddComboBox = whatToAddComboBox;

		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));

		titleLabel = new Label("Full name");
		titleTextField = new TextField();

		languageLabel = new Label("Language");
		languageTextField = new TextField();

		publisherLabel = new Label("Publisher");
		publisherTextField = new TextField();

		yearLabel = new Label("Year");
		yearTextField = new TextField();

		// genre
		genreLabel = new Label("Genre");
		addAnotherGenreTF = new Button("Add genre");
		this.add(genreLabel, 3, 1);
		this.add(addAnotherGenreTF, 4, 1);

		// author
		authorLabel = new Label("Author");
		addAnotherAuthorTF = new Button("Add author");
		this.add(authorLabel, 6, 1);
		this.add(addAnotherAuthorTF, 7, 1);

		
		
		
		// add book button
		addButton = new Button("Add to database");
		handler = new AddBookHandler(whatToAddComboBox);

		addButton.setOnAction(handler);

		// add things to grid
		// this.add(child, col, row);
		this.add(titleLabel, 1, 2);
		this.add(publisherLabel, 1, 3);
		this.add(languageLabel, 1, 4);
		this.add(yearLabel, 1, 5);

		this.add(titleTextField, 2, 2);
		this.add(publisherTextField, 2, 3);
		this.add(languageTextField, 2, 4);
		this.add(yearTextField, 2, 5);

		this.add(addButton, 2, 7);

	}

}

class AddTextFieldToFormAndListHandler implements EventHandler<ActionEvent> {

	GridPane mainGrid;
	List<TextField> tfList;
	int col;
	int startRow;
	
	AddTextFieldToFormAndListHandler(GridPane mainGrid, List<TextField> tfList, int col, int startRow) {
		this.mainGrid = mainGrid;
		this.tfList = tfList;
		this.col = col;
		this.startRow = startRow;
		
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		
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
			// String query = "SELECT create_or_get_librarian_id('" +
			// name.getText() + "', '" + login.getText() + "', '" +
			// password.getText() + "');";
			// stmt.executeQuery(query);
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
