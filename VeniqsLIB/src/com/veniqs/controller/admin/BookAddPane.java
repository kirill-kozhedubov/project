package com.veniqs.controller.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.layout.HBox;

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
	private List<TextField> genreTextFields = new ArrayList<>();
	private Button addAnotherGenreTextFieldButton;

	private Label authorLabel;
	private List<TextField> authorTextFields = new ArrayList<>();
	private Button addAnotherAuthorTextFieldButton;

	private Button addButton;

	private ComboBox<String> whatToAddComboBox;

	private EventHandler<ActionEvent> handler;

	public BookAddPane(ComboBox<String> whatToAddComboBox) {
		this.whatToAddComboBox = whatToAddComboBox;

		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));

		titleLabel = new Label("Title");
		titleTextField = new TextField();

		languageLabel = new Label("Language");
		languageTextField = new TextField();

		publisherLabel = new Label("Publisher");
		publisherTextField = new TextField();

		yearLabel = new Label("Year");
		yearTextField = new TextField();

		// genre
		genreLabel = new Label("Genre");
		addAnotherGenreTextFieldButton = new Button("Add");
		HBox genreBox = new HBox(genreLabel, addAnotherGenreTextFieldButton);
		genreBox.setSpacing(5);
		this.add(genreBox, 4, 1);

		// author
		authorLabel = new Label("Author");
		addAnotherAuthorTextFieldButton = new Button("Add");
		HBox authorBox = new HBox(authorLabel, addAnotherAuthorTextFieldButton);
		authorBox.setSpacing(5);
		this.add(authorBox, 6, 1);

		// add book button
		addButton = new Button("Add to database");
		handler = new AddBookHandler(whatToAddComboBox, titleTextField, languageTextField, publisherTextField,
				yearTextField, genreTextFields, authorTextFields);
		addButton.setOnAction(handler);

		// add things to grid

		// genre and author
		AddTextFieldToFormAndListHandler authorAdd = new AddTextFieldToFormAndListHandler(this, authorTextFields, 6, 2);
		AddTextFieldToFormAndListHandler genreAdd = new AddTextFieldToFormAndListHandler(this, genreTextFields, 4, 2);

		addAnotherAuthorTextFieldButton.setOnAction(authorAdd);
		addAnotherGenreTextFieldButton.setOnAction(genreAdd);

		// labels
		this.add(titleLabel, 1, 2);
		this.add(publisherLabel, 1, 3);
		this.add(languageLabel, 1, 4);
		this.add(yearLabel, 1, 5);
		// tf's
		this.add(titleTextField, 2, 2);
		this.add(publisherTextField, 2, 3);
		this.add(languageTextField, 2, 4);
		this.add(yearTextField, 2, 5);
		// buttons
		this.add(addButton, 2, 7);

	}

}

class AddTextFieldToFormAndListHandler implements EventHandler<ActionEvent> {

	GridPane mainGrid;
	List<TextField> tfList;
	int col;
	int row;

	AddTextFieldToFormAndListHandler(GridPane mainGrid, List<TextField> tfList, int col, int row) {
		this.mainGrid = mainGrid;
		this.tfList = tfList;
		this.col = col;
		this.row = row;

	}

	@Override
	public void handle(ActionEvent arg0) {
		TextField tf = new TextField();
		tf.setMaxWidth(100);
		tf.setMinWidth(70);
		mainGrid.add(tf, col, row);
		row++;
		tfList.add(tf);
	}

}

class AddBookHandler implements EventHandler<ActionEvent> {

	private ComboBox<String> comboBox;
	TextField title;
	TextField language;
	TextField publisher;
	TextField year;
	List<TextField> genre;
	List<TextField> author;

	AddBookHandler(ComboBox<String> comboBox, TextField title, TextField language, TextField publisher, TextField year,
			List<TextField> genre, List<TextField> author) {
		this.comboBox = comboBox;
		this.title = title;
		this.language = language;
		this.publisher = publisher;
		this.year = year;
		this.genre = genre;
		this.author = author;

	}

	@Override
	public void handle(ActionEvent arg0) {
		int bookID = addBook();
		
	}

	private int addBook() {
		int addedOrOverridenBookID = -1;
		String titleStr = title.getText();
		String languageStr = language.getText();
		String publisherStr = publisher.getText();
		String yearStr = year.getText();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			System.out.println("Opened database successfully Book add pane");
			Statement stmt = c.createStatement();
			String query = "SELECT book_create_or_get_id('" + titleStr + "', " + yearStr + ", '" + languageStr + "', '"
					+ publisherStr + "');";
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				addedOrOverridenBookID = rs.getInt(rs.getMetaData().getColumnName(1));
				System.out.println(addedOrOverridenBookID + " " + rs.getMetaData().getColumnName(1));
			}
			rs.close();

			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ОШИБКА " + e.getClass().getName() + ": " + e.getMessage());
		}

		return addedOrOverridenBookID;
	}
	
	private int addGenresToBook() {
		return 0;
	}
	
	private int addAuthorsToBook() {
		return 0;
	}
	

}
