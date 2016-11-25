package com.veniqs.controller.librarian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.veniqs.controller.db.DBConnector;
import com.veniqs.model.Book;
import com.veniqs.model.BookAuthor;
import com.veniqs.model.BookGenre;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AuthorTableCreator implements TableCreator{

	private TableView<BookAuthor> dataTable;
	private ObservableList<BookAuthor> dataList;

	private TableColumn<BookAuthor, Integer> colID;
	private TableColumn<BookAuthor, String> colName;

	private BookAuthor selectedAuthor;
	
	public AuthorTableCreator() {
		// create table + set items
		dataList = getData();
		dataTable = new TableView<BookAuthor>();

		// create cols
		TableColumn idCol = new TableColumn("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<BookAuthor, Integer>("id"));
		idCol.setPrefWidth(50);

		TableColumn nameCol = new TableColumn("Full Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<BookAuthor, String>("name"));
		nameCol.setPrefWidth(550);

		// set columbs
		dataTable.getColumns().setAll(idCol, nameCol);
		dataTable.setPrefWidth(600);
		dataTable.setPrefHeight(300);
	
		dataTable.setItems(dataList);
		dataTable.getSelectionModel().selectedItemProperty().addListener(getListener());
		
	}
	public BookAuthor getSelectedAuthor() {
		return selectedAuthor;
	}
	
	public TableView<BookAuthor> getDataTable(String str) {
		dataTable.setItems(getData(str));
		return dataTable;
	}
	
	private ChangeListener<BookAuthor> getListener() {
		ChangeListener<BookAuthor> listener = new ChangeListener<BookAuthor>() {

			@Override
			public void changed(ObservableValue<? extends BookAuthor> observable, BookAuthor oldValue, BookAuthor newValue) {
				selectedAuthor = newValue;
			}
		};

		return listener;
	}
	public  ObservableList<BookAuthor> getData() {
		ObservableList<BookAuthor> data = FXCollections.observableArrayList();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			Statement stmt = c.createStatement();
			String query = "SELECT * FROM book_author;";
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("full_name");
				BookAuthor ap = new BookAuthor(id, name);
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
	
	public  ObservableList<BookAuthor> getData(String full_name) {
		ObservableList<BookAuthor> data = FXCollections.observableArrayList();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			Statement stmt = c.createStatement();
			String query = "SELECT * FROM book_author where full_name LIKE '%" + full_name + "%';";
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("full_name");
				BookAuthor ap = new BookAuthor(id, name);
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

	public TableView<BookAuthor> getTable() {
		return dataTable;
	}
}
