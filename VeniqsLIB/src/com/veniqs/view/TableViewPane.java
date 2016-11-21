package com.veniqs.view;

import com.veniqs.controller.librarian.AuthorTableCreator;
import com.veniqs.controller.librarian.GenreTableCreator;
import com.veniqs.controller.librarian.PublisherTableCreator;

import javafx.scene.layout.HBox;

public class TableViewPane extends HBox {

	public TableViewPane() {
		// PublisherTableCreator ptc = new PublisherTableCreator();
		// this.getChildren().add(ptc.getTable());

		// AuthorTableCreator atc = new AuthorTableCreator();
		// this.getChildren().add(atc.getTable());

		GenreTableCreator atc = new GenreTableCreator();
		this.getChildren().add(atc.getTable());
	}
}
