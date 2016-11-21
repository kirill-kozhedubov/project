package com.veniqs.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookAuthor {

	private SimpleIntegerProperty id;
	private SimpleStringProperty name;

	public BookAuthor(int idC, String nameC) {
		id = new SimpleIntegerProperty(idC);
		name = new SimpleStringProperty(nameC);
	}

	public int getId() {
		return id.get();
	}

	public String getName() {
		return name.get();
	}

}
