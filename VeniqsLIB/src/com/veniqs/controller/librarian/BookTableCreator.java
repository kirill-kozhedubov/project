package com.veniqs.controller.librarian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.veniqs.controller.db.DBConnector;
import com.veniqs.model.Book;
import com.veniqs.model.Book;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookTableCreator implements TableCreator {

	private TableView<Book> dataTable;
	private ObservableList<Book> dataList;
	private Book selectedBook;

	public TableView<Book> getDataTable() {
		return dataTable;
	}

	public Book getSelectedBook() {
		return selectedBook;
	}
	
	public BookTableCreator(boolean checkForTaken ) {
		// create table + set items
		dataList = getData(checkForTaken);
		dataTable = new TableView<Book>();

		// create cols
		TableColumn idCol = new TableColumn("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
		idCol.setPrefWidth(20);

		TableColumn titleCol = new TableColumn("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		titleCol.setPrefWidth(200);

		TableColumn langCol = new TableColumn("Language");
		langCol.setCellValueFactory(new PropertyValueFactory<Book, String>("language"));
		langCol.setPrefWidth(100);

		TableColumn publisherCol = new TableColumn("Publisher");
		publisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
		publisherCol.setPrefWidth(150);

		TableColumn dateCol = new TableColumn("Release");
		dateCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("publicationDate"));
		dateCol.setPrefWidth(60);
		
		TableColumn takenCol = new TableColumn("Taken");
		takenCol.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("isTaken"));
		takenCol.setPrefWidth(70);

		// set columbs
		dataTable.getColumns().setAll(idCol, titleCol, langCol, publisherCol, dateCol, takenCol);
		dataTable.setPrefWidth(600);
		dataTable.setPrefHeight(300);

		dataTable.setItems(dataList);
		dataTable.getSelectionModel().selectedItemProperty().addListener(getListener());

		// dataTable.getSelectionModel().selectedIndexProperty().addListener(new
		// RowSelectChangeListener());
	}

	private ChangeListener<Book> getListener() {
		ChangeListener<Book> listener = new ChangeListener<Book>() {

			@Override
			public void changed(ObservableValue<? extends Book> observable, Book oldValue, Book newValue) {
				selectedBook = newValue;
				//System.out.println(getSelectedBook());
			}
		};

		return listener;
	}

	public ObservableList<Book> getData(boolean checkForTaken) {
		ObservableList<Book> data = FXCollections.observableArrayList();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			Statement stmt = c.createStatement();
			String query = "";
			if (checkForTaken) {
				query = "SELECT b.id, b.title, p.name as publ_name, l.name as lang_name, b.publication_date as publ_date "  + " , b.is_taken_flag as taken "
						+ " FROM book b " + " JOIN publisher p " + " ON b.publisher_id = p.id " + " JOIN language l "
						+ " ON b.language_id = l.id " 
						+ " WHERE b.is_taken_flag = 'false' "
						+ " ORDER BY b.id; ";
			} else {
				query = "SELECT b.id, b.title, p.name as publ_name, l.name as lang_name, b.publication_date as publ_date "  + " , b.is_taken_flag as taken "
						+ " FROM book b " + " JOIN publisher p " + " ON b.publisher_id = p.id " + " JOIN language l "
						+ " ON b.language_id = l.id " + " ORDER BY b.id; ";
			}
			
			
			
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String language = rs.getString("lang_name");
				String publisher = rs.getString("publ_name");
				int date = rs.getInt("publ_date");
				boolean isTaken = rs.getBoolean("taken");
				Book ap = new Book(id, title, language, publisher, date, isTaken);
				data.add(ap);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ОШИБКА " + e.getClass().getName() + ": " + e.getMessage());
		}

		return data;
	}

	public TableView<Book> getTable() {
		return dataTable;
	}
}
