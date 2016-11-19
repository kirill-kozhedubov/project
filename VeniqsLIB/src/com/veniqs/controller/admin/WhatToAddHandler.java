package com.veniqs.controller.admin;

import com.veniqs.view.AdminPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class WhatToAddHandler implements ChangeListener<String> {

	ComboBox<String> whatToAddBox;
	VBox topPane;

	public WhatToAddHandler(ComboBox<String> whatToAddBox, VBox topPane) {
		this.whatToAddBox = whatToAddBox;
		this.topPane = topPane;
	}

	@Override
	public void changed(ObservableValue<? extends String> readOnlyVal, String oldVal, String newVal) {
		switch (newVal) {
		case "Publisher":
			System.out.println("Publisher chosen");
			clearListAndAddPane(new OnlyNameAddPane(whatToAddBox));
			break;
		case "Author":
			System.out.println("Author chosen");
			clearListAndAddPane(new OnlyNameAddPane(whatToAddBox));
			break;
		case "Language":
			System.out.println("Language chosen");
			clearListAndAddPane(new OnlyNameAddPane(whatToAddBox));
			break;
		case "Genre":
			System.out.println("Genre chosen");
			clearListAndAddPane(new OnlyNameAddPane(whatToAddBox));
			break;
		case "Customer":
			System.out.println("Customer chosen");
			clearListAndAddPane(new OnlyNameAddPane(whatToAddBox));
			break;
		case "Book":
			System.out.println("Book chosen");
			clearListAndAddPane(new BookAddPane(whatToAddBox));
			break;
		case "Librarian":
			System.out.println("Librarian chosen");
			clearListAndAddPane(new LibrarianAddPane(whatToAddBox));
			break;
		case "Logbook":
			System.out.println("Logbook chosen");
			clearListAndAddPane(null);
			break;
		default:
			break;
		}
	}

	
	private void clearListAndAddPane(GridPane grid) {
		topPane.getChildren().clear();
		topPane.getChildren().add(grid);
	}
	
}
