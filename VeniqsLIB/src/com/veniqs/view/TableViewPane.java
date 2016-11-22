package com.veniqs.view;

import com.veniqs.controller.librarian.AuthorTableCreator;
import com.veniqs.controller.librarian.BookTableCreator;
import com.veniqs.controller.librarian.CustomerTableCreator;
import com.veniqs.controller.librarian.GenreTableCreator;
import com.veniqs.controller.librarian.LanguageTableCreator;
import com.veniqs.controller.librarian.LibrarianTableCreator;
import com.veniqs.controller.librarian.LogbookTableCreator;
import com.veniqs.controller.librarian.PublisherTableCreator;
import com.veniqs.controller.librarian.TableCreator;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class TableViewPane extends HBox {
	Node table;

	public TableViewPane(String type) {
		table = getTable(type);
		this.getChildren().add(table);
	}

	public static BookTableCreator getBtc(boolean takenCheck) {
		return new BookTableCreator(takenCheck);
	}
	
	public static LogbookTableCreator getLtc() {
		return new LogbookTableCreator();
	}
	
	
	public Node getTable() {
		return table;
	}

	private Node getTable(String type) {
		TableCreator tableCreator = null;
		switch (type) {
		case "Publisher":
			System.out.println("Publisher chosen");
			tableCreator = new PublisherTableCreator();
			break;
		case "Author":
			System.out.println("Author chosen");
			tableCreator = new AuthorTableCreator();
			break;
		case "Language":
			System.out.println("Language chosen");
			tableCreator = new LanguageTableCreator();
			break;
		case "Genre":
			System.out.println("Genre chosen");
			tableCreator = new GenreTableCreator();
			break;
		case "Customer":
			System.out.println("Customer chosen");
			tableCreator = new CustomerTableCreator();
			break;
		case "Book":
			System.out.println("Book chosen");
			tableCreator = new BookTableCreator(false);
			break;
		case "Librarian":
			System.out.println("Librarian chosen");
			tableCreator = new LibrarianTableCreator();
			break;
		case "Logbook":
			System.out.println("Logbook chosen");
			tableCreator = new BookTableCreator(true);
			break;
		case "Logbook update":
			System.out.println("Logbook update chosen");
			tableCreator = new LogbookTableCreator();
			break;
		}

		return tableCreator.getTable();
	}

}
