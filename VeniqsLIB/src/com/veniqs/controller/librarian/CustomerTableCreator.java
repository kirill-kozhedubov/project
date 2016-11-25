package com.veniqs.controller.librarian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.veniqs.controller.db.DBConnector;
import com.veniqs.model.Book;
import com.veniqs.model.Customer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerTableCreator implements TableCreator{

	private TableView<Customer> dataTable;
	private ObservableList<Customer> dataList;

	private TableColumn<Customer, Integer> colID;
	private TableColumn<Customer, String> colName;

	private Customer selectedCustomer;
	public CustomerTableCreator() {
		// create table + set items
		dataList = getData();
		dataTable = new TableView<Customer>();

		// create cols
		TableColumn idCol = new TableColumn("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
		idCol.setPrefWidth(50);

		TableColumn nameCol = new TableColumn("Full Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		nameCol.setPrefWidth(550);

		// set columbs
		dataTable.getColumns().setAll(idCol, nameCol);
		dataTable.setPrefWidth(600);
		dataTable.setPrefHeight(300);

		dataTable.setItems(dataList);
		dataTable.getSelectionModel().selectedItemProperty().addListener(getListener());
		
	}
	
	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}
	
	private ChangeListener<Customer> getListener() {
		ChangeListener<Customer> listener = new ChangeListener<Customer>() {

			@Override
			public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {
				selectedCustomer = newValue;
			}
		};

		return listener;
	}
	
	public ObservableList<Customer> getData() {
		ObservableList<Customer> data = FXCollections.observableArrayList();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			Statement stmt = c.createStatement();
			String query = "SELECT * FROM customer;";
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("full_name");
				Customer ap = new Customer(id, name);
				data.add(ap);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ОШИБКА " + e.getClass().getName() + ": " + e.getMessage());
		}

		return data;
	}

	public TableView<Customer> getTable() {
		return dataTable;
	}
}
