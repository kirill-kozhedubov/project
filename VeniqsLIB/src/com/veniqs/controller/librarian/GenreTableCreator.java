package com.veniqs.controller.librarian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.veniqs.controller.db.DBConnector;
import com.veniqs.model.BookGenre;
import com.veniqs.model.Customer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GenreTableCreator implements TableCreator {

	private TableView<BookGenre> dataTable;
	private ObservableList<BookGenre> dataList;

	private TableColumn<BookGenre, Integer> colID;
	private TableColumn<BookGenre, String> colName;

	private BookGenre selectedGenre;

	public GenreTableCreator() {
		// create table + set items
		dataList = getData();
		dataTable = new TableView<BookGenre>();

		// create cols
		TableColumn idCol = new TableColumn("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<BookGenre, Integer>("id"));
		idCol.setPrefWidth(50);

		TableColumn nameCol = new TableColumn("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<BookGenre, String>("name"));
		nameCol.setPrefWidth(550);

		// set columbs
		dataTable.getColumns().setAll(idCol, nameCol);
		dataTable.setPrefWidth(600);
		dataTable.setPrefHeight(300);

		dataTable.setItems(dataList);

		dataTable.getSelectionModel().selectedItemProperty().addListener(getListener());
	}

	public BookGenre getSelectedGenre() {
		return selectedGenre;
	}

	private ChangeListener<BookGenre> getListener() {
		ChangeListener<BookGenre> listener = new ChangeListener<BookGenre>() {

			@Override
			public void changed(ObservableValue<? extends BookGenre> observable, BookGenre oldValue,
					BookGenre newValue) {

				selectedGenre = newValue;

			}
		};

		return listener;
	}

	public TableView<BookGenre> getDataTable(String str) {
		dataTable.setItems(getData(str));
		return dataTable;
	}
	
	
	public ObservableList<BookGenre> getData() {
		ObservableList<BookGenre> data = FXCollections.observableArrayList();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			Statement stmt = c.createStatement();
			String query = "SELECT * FROM book_genre;";
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				BookGenre ap = new BookGenre(id, name);
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

	public ObservableList<BookGenre> getData(String full_name) {
		ObservableList<BookGenre> data = FXCollections.observableArrayList();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			Statement stmt = c.createStatement();
			String query = "SELECT * FROM book_genre where name like '%" + full_name + "%';";
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				BookGenre ap = new BookGenre(id, name);
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

	public TableView<BookGenre> getTable() {
		return dataTable;
	}
}
