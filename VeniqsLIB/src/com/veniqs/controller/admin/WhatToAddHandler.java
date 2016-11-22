package com.veniqs.controller.admin;

import com.veniqs.controller.librarian.BookTableCreator;
import com.veniqs.controller.librarian.LogbookTableCreator;
import com.veniqs.model.Book;
import com.veniqs.view.AdminPane;
import com.veniqs.view.TableViewPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class WhatToAddHandler implements ChangeListener<String> {

	ComboBox<String> whatToAddBox;
	VBox topPane;
	HBox botPane;

	public WhatToAddHandler(ComboBox<String> whatToAddBox, VBox topPane, HBox botPane) {
		this.whatToAddBox = whatToAddBox;
		this.topPane = topPane;
		this.botPane = botPane;
	}

	@Override
	public void changed(ObservableValue<? extends String> readOnlyVal, String oldVal, String newVal) {
		switch (newVal) {
		case "Publisher":
			System.out.println("Publisher chosen");
			clearListAndAddPane(new OnlyNameAddPane(whatToAddBox));
			clearListAndAddPaneTables(newVal);
			break;
		case "Author":
			System.out.println("Author chosen");
			clearListAndAddPane(new OnlyNameAddPane(whatToAddBox));
			clearListAndAddPaneTables(newVal);
			break;
		case "Language":
			System.out.println("Language chosen");
			clearListAndAddPane(new OnlyNameAddPane(whatToAddBox));
			clearListAndAddPaneTables(newVal);
			break;
		case "Genre":
			System.out.println("Genre chosen");
			clearListAndAddPane(new OnlyNameAddPane(whatToAddBox));
			clearListAndAddPaneTables(newVal);
			break;
		case "Customer":
			System.out.println("Customer chosen");
			clearListAndAddPane(new OnlyNameAddPane(whatToAddBox));
			clearListAndAddPaneTables(newVal);
			break;
		case "Book":
			System.out.println("Book chosen");
			clearListAndAddPane(new BookAddPane(whatToAddBox));
			clearListAndAddPaneTables(newVal);
			break;
		case "Librarian":
			System.out.println("Librarian chosen");
			clearListAndAddPane(new LibrarianAddPane(whatToAddBox));
			clearListAndAddPaneTables(newVal);
			break;
		case "Logbook":
			System.out.println("Logbook chosen");
			HBox hb = new HBox();
			botPane.getChildren().clear();
			botPane.getChildren().add(hb);
			BookTableCreator btc = TableViewPane.getBtc(true);
			clearListAndAddPane(new LogbookAddPane(btc));
			hb.getChildren().add(btc.getDataTable());
			break;
		case "Logbook update":
			System.out.println("Logbook update chosen");
			HBox hbb = new HBox();
			botPane.getChildren().clear();
			botPane.getChildren().add(hbb);
			LogbookTableCreator ltc = TableViewPane.getLtc();
			clearListAndAddPane(new LogbookReturnPane(ltc));
			hbb.getChildren().add(ltc.getTable());
			break;
		default:
			break;
		}

	}

	private void clearListAndAddPaneTables(String type) {
		botPane.getChildren().clear();
		botPane.getChildren().add(new TableViewPane(type));
	}

	private void clearListAndAddPane(GridPane grid) {
		topPane.getChildren().clear();
		topPane.getChildren().add(grid);
	}

}
