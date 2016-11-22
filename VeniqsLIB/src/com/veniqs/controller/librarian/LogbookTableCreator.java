package com.veniqs.controller.librarian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.OffsetDateTime;

import com.veniqs.controller.db.DBConnector;
import com.veniqs.model.Book;
import com.veniqs.model.Logbook;
import com.veniqs.model.Book;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LogbookTableCreator implements TableCreator {
	private Logbook selectedLogbook;
	private TableView<Logbook> dataTable;
	private ObservableList<Logbook> dataList;

	public LogbookTableCreator() {
		// create table + set items
		dataList = getData();
		dataTable = new TableView<Logbook>();

		// create cols
		TableColumn codeCol = new TableColumn("Code");
		codeCol.setCellValueFactory(new PropertyValueFactory<Logbook, Integer>("code"));
		codeCol.setPrefWidth(80);

		TableColumn bookCol = new TableColumn("Book");
		bookCol.setCellValueFactory(new PropertyValueFactory<Logbook, Integer>("bookTitle"));
		bookCol.setPrefWidth(150);

		TableColumn customerCol = new TableColumn("Customer");
		customerCol.setCellValueFactory(new PropertyValueFactory<Logbook, Integer>("customer"));
		customerCol.setPrefWidth(80);

		TableColumn librarianCol = new TableColumn("Librarian");
		librarianCol.setCellValueFactory(new PropertyValueFactory<Logbook, Integer>("librarian"));
		librarianCol.setPrefWidth(90);

		TableColumn takenDateCol = new TableColumn("Taken");
		takenDateCol.setCellValueFactory(new PropertyValueFactory<Logbook, Integer>("takenDate"));
		takenDateCol.setPrefWidth(80);

		TableColumn expiredDateCol = new TableColumn("Expire");
		expiredDateCol.setCellValueFactory(new PropertyValueFactory<Logbook, Integer>("expiredDate"));
		expiredDateCol.setPrefWidth(80);

		TableColumn isReturnedCol = new TableColumn("Re");
		isReturnedCol.setCellValueFactory(new PropertyValueFactory<Logbook, Integer>("isReturned"));
		isReturnedCol.setPrefWidth(40);

		// set columbs
		dataTable.getColumns().setAll(codeCol, bookCol, customerCol, librarianCol, takenDateCol, expiredDateCol,
				isReturnedCol);
		dataTable.setPrefWidth(600);
		dataTable.setPrefHeight(300);

		dataTable.setItems(dataList);

		// dataTable.getSelectionModel().selectedIndexProperty().addListener(new
		// RowSelectChangeListener());
		dataTable.getSelectionModel().selectedItemProperty().addListener(getListener());
	}

	private ChangeListener<Logbook> getListener() {
		ChangeListener<Logbook> listener = new ChangeListener<Logbook>() {

			@Override
			public void changed(ObservableValue<? extends Logbook> observable, Logbook oldValue, Logbook newValue) {
				selectedLogbook = newValue;
				// System.out.println(getSelectedBook());
			}
		};

		return listener;
	}

	public Logbook getSelectedLogbook() {
		return selectedLogbook;
	}

	public ObservableList<Logbook> getData() {
		ObservableList<Logbook> data = FXCollections.observableArrayList();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			Statement stmt = c.createStatement();
			String query = " SELECT " + " logbook.individual_code, " + " logbook.is_returned_flag, " + " book.title, "
					+ " librarian.full_name as lib_name, " + " customer.full_name as cust_name,"
					+ " logbook.creation_date," + " logbook.expiration_date" + " FROM logbook"
					+ " JOIN book ON logbook.book_id = book.id"
					+ " JOIN librarian ON logbook.librarian_id = librarian.id"
					+ " JOIN customer ON logbook.customer_id = customer.id"
					+ " WHERE logbook.is_returned_flag = 'false'" + " ORDER BY logbook.individual_code;";

			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

				String code = rs.getString("individual_code");
				boolean isReturned = rs.getBoolean("is_returned_flag");
				String bTitle = rs.getString("title");
				String custName = rs.getString("cust_name");
				String libName = rs.getString("lib_name");
				OffsetDateTime creationDate = rs.getObject("creation_date", OffsetDateTime.class);
				OffsetDateTime expirationDate = rs.getObject("expiration_date", OffsetDateTime.class);
				String creationDateStr = creationDate.toLocalDate().toString();
				String expirationDateStr = expirationDate.toLocalDate().toString();
				Logbook ap = new Logbook(code, bTitle, custName, libName, creationDateStr, expirationDateStr,
						isReturned);
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

	public TableView<Logbook> getTable() {
		return dataTable;
	}
}
