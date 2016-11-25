package com.veniqs.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookGenre {

	@Override
	public String toString() {
		return "BookGenre [id=" + id + ", name=" + name + "]";
	}

	private SimpleIntegerProperty id;
	private SimpleStringProperty  name;

	public BookGenre(int idC, String nameC) {
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
