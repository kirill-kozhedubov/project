package com.veniqs.controller.admin;

import java.util.Random;

import com.veniqs.controller.librarian.BookTableCreator;
import com.veniqs.model.Book;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LogbookAddPane extends GridPane {

	private TableView<Book> bookTable;
	Book selectedBook;

	private Label customerLabel;
	private TextField customerTextField;

	private ListView<BookListView> list;
	private ObservableList<BookListView> bookList;

	private Button addBookButton;
	private Button issueBooks;

	public LogbookAddPane(BookTableCreator btCreator) {
		this.bookTable = btCreator.getDataTable();
		bookTable.getSelectionModel().setCellSelectionEnabled(true);
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));

		customerLabel = new Label("Customer name");
		customerTextField = new TextField();
		list = new ListView<BookListView>(getEmptyList());
		list.setPrefWidth(150);
		list.setPrefHeight(100);

		addBookButton = new Button("Add book");
		issueBooks = new Button("Add logbook to db");
		addBookButton.setOnAction(getListenerForBookAdd(btCreator, list, bookList));
		issueBooks.setOnAction(getListenerForLogbookAdd(customerTextField, bookList));
		/*
		 * addButton = new Button("Add to database"); handler = new
		 * AddLibrarianHandler(whatToAddComboBox, nameTextField, loginTextField,
		 * passwordTextField); addButton.setOnAction(handler);
		 */

		this.add(customerLabel, 1, 1);
		this.add(customerTextField, 2, 1);
		this.add(list, 3, 2);
		this.add(addBookButton, 2, 2);
		this.add(issueBooks, 2, 5);

	}

	private EventHandler<ActionEvent> getListenerForBookAdd(BookTableCreator btCreator, ListView<BookListView> list,
			ObservableList<BookListView> bookList) {
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Book book = btCreator.getSelectedBook();
				// System.out.println(book + " book");
				BookListView bookListView = new BookListView(book.getTitle(), book.getId());
				System.out.println(bookListView + " booklist");
				bookList.add(bookListView);
				list.setItems(bookList);
				list.refresh();

			}
		};
		return handler;
	}

	private EventHandler<ActionEvent> getListenerForLogbookAdd(TextField customer, ObservableList<BookListView> list) {
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String customerStr = customer.getText();
				Random rnd = new Random();
				String code = "";
				for (int i = 0; i < 10; i++) {
					code += "" + rnd.nextInt(9) + "";	
				}
				for (BookListView bookListView : list) {
					
				}
				
			}
		};
		return handler;

	}

	private ObservableList<BookListView> getEmptyList() {
		bookList = FXCollections.observableArrayList();
		return bookList;
	}
}

class BookListView {
	String title;
	int id;

	public BookListView(String title, int id) {
		this.title = title;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return id + " " + title;
	}
}