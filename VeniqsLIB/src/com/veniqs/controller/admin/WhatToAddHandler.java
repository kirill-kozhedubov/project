package com.veniqs.controller.admin;

import com.veniqs.view.AdminPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
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
			System.out.println("publisher chosen");

			break;
		case "Author":

			break;
		case "Language":

			break;
		case "Genre":

			break;
		case "Customer":

			break;
		case "Book":

			break;
		case "Librarian":

			break;
		case "Logbook":

			break;
		default:
			break;
		}
	}

}
