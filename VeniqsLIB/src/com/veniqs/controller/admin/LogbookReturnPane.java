package com.veniqs.controller.admin;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.veniqs.controller.db.DBConnector;
import com.veniqs.controller.librarian.LogbookTableCreator;
import com.veniqs.model.Book;
import com.veniqs.model.Logbook;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LogbookReturnPane extends GridPane {

	private Label codeLabel;
	private TextField codeTextField;

	private Label messageLabel;

	private Button applyButton;
	LogbookTableCreator ltc;
	private EventHandler<ActionEvent> handler;

	public LogbookReturnPane(LogbookTableCreator ltc) {
		this.ltc = ltc;
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));

		applyButton = new Button("Book returned!");
		applyButton.setMinSize(300, 300);
		handler = getListenerButton();
		applyButton.setOnAction(handler);


		this.add(applyButton, 2, 2);

	}

	private EventHandler<ActionEvent> getListenerButton() {
		EventHandler<ActionEvent> listener = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Logbook logbook = ltc.getSelectedLogbook();
				try {
					DBConnector connection = new DBConnector();
					Connection c = connection.getConnection();
					Statement stmt = c.createStatement();
					stmt.executeUpdate("UPDATE logbook SET is_returned_flag = 'true' WHERE individual_code = '"
							+ logbook.getCode() + "';");
					stmt.executeUpdate(
							"UPDATE book SET is_taken_flag = 'false' WHERE title = '" + logbook.getBookTitle() + "';");
					stmt.close();
					c.commit();
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("ОШИБКА " + e.getClass().getName() + ": " + e.getMessage());
				}
				ltc.getTable().setItems(ltc.getData());
			}

		};
		return listener;
	}
}
