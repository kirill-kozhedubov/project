package com.veniqs.controller.librarian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.veniqs.controller.db.DBConnector;
import com.veniqs.model.LibrarianTableModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LibrarianTableCreator implements TableCreator {

	private TableView<LibrarianTableModel> dataTable;
	private ObservableList<LibrarianTableModel> dataList;

	public LibrarianTableCreator() {
		// create table + set items
		dataList = getData();
		dataTable = new TableView<LibrarianTableModel>();

		// create cols
		TableColumn idCol = new TableColumn("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<LibrarianTableModel, Integer>("id"));
		idCol.setPrefWidth(50);

		TableColumn nameCol = new TableColumn("Full Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<LibrarianTableModel, String>("fullName"));
		nameCol.setPrefWidth(250);
		
		TableColumn loginCol = new TableColumn("Login");
		loginCol.setCellValueFactory(new PropertyValueFactory<LibrarianTableModel, String>("login"));
		loginCol.setPrefWidth(150);
		
		TableColumn passwordCol = new TableColumn("Password");
		passwordCol.setCellValueFactory(new PropertyValueFactory<LibrarianTableModel, String>("password"));
		passwordCol.setPrefWidth(150);

		// set columbs
		dataTable.getColumns().setAll(idCol, nameCol, loginCol, passwordCol);
		dataTable.setPrefWidth(600);
		dataTable.setPrefHeight(300);

		dataTable.setItems(dataList);

		// dataTable.getSelectionModel().selectedIndexProperty().addListener(new
		// RowSelectChangeListener());
	}

	private ObservableList<LibrarianTableModel> getData() {
		ObservableList<LibrarianTableModel> data = FXCollections.observableArrayList();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			Statement stmt = c.createStatement();
			String query = "SELECT * FROM librarian;";
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("full_name");
				String login = rs.getString("login");
				String password = rs.getString("password");
				LibrarianTableModel ap = new LibrarianTableModel(id, name, login, password);
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

	public TableView<LibrarianTableModel> getTable() {
		return dataTable;
	}
}
