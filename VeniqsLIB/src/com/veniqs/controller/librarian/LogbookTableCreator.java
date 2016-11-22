package com.veniqs.controller.librarian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.veniqs.controller.db.DBConnector;
import com.veniqs.model.Book;
import com.veniqs.model.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LogbookTableCreator implements TableCreator {

	private TableView<Book> dataTable;
	private ObservableList<Book> dataList;

	public LogbookTableCreator() {
		// create table + set items
		dataList = getData();
		dataTable = new TableView<Book>();

		// create cols
		TableColumn idCol = new TableColumn("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
		idCol.setPrefWidth(30);

		TableColumn titleCol = new TableColumn("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		titleCol.setPrefWidth(150);

		TableColumn langCol = new TableColumn("Language");
		langCol.setCellValueFactory(new PropertyValueFactory<Book, String>("language"));
		langCol.setPrefWidth(100);

		TableColumn publisherCol = new TableColumn("Publisher");
		publisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
		publisherCol.setPrefWidth(120);

		TableColumn dateCol = new TableColumn("Release");
		dateCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("publicationDate"));
		dateCol.setPrefWidth(50);

		// set columbs
		dataTable.getColumns().setAll(idCol, titleCol, langCol, publisherCol, dateCol);
		dataTable.setPrefWidth(450);
		dataTable.setPrefHeight(300);

		dataTable.setItems(dataList);

		// dataTable.getSelectionModel().selectedIndexProperty().addListener(new
		// RowSelectChangeListener());
	}

	private ObservableList<Book> getData() {
		ObservableList<Book> data = FXCollections.observableArrayList();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			Statement stmt = c.createStatement();
			String query = "SELECT b.id, b.title, p.name as publ_name, l.name as lang_name, b.publication_date as publ_date "
					+ " FROM book b " + " JOIN publisher p " + " ON b.publisher_id = p.id " + " JOIN language l "
					+ " ON b.language_id = l.id " + " ORDER BY b.id; ";

			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String language = rs.getString("lang_name");
				String publisher = rs.getString("publ_name");
				int date = rs.getInt("publ_date");
				Book ap = new Book(id, title, language, publisher, date);
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
