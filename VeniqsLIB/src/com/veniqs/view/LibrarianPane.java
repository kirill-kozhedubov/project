package com.veniqs.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.veniqs.controller.admin.OnlyNameAddPane;
import com.veniqs.controller.db.DBConnector;
import com.veniqs.controller.librarian.AuthorTableCreator;
import com.veniqs.controller.librarian.BookTableCreator;
import com.veniqs.controller.librarian.GenreTableCreator;
import com.veniqs.controller.librarian.LanguageTableCreator;
import com.veniqs.controller.librarian.LogbookTableCreator;
import com.veniqs.controller.librarian.PublisherTableCreator;
import com.veniqs.controller.librarian.TableCreator;
import com.veniqs.model.Book;
import com.veniqs.model.BookAuthor;
import com.veniqs.model.BookGenre;
import com.veniqs.model.BookPublisher;
import com.veniqs.model.Language;
import com.veniqs.model.Librarian;
import com.veniqs.model.Logbook;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibrarianPane extends Stage {
	private static volatile LibrarianPane instance;
	public static Librarian libr;

	public static LibrarianPane getInstance(Librarian lib) {
		libr = lib;
		LibrarianPane localInstance = instance;
		if (localInstance == null) {
			synchronized (LibrarianPane.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new LibrarianPane(lib);

				}
			}
		} else {
			instance.show();
		}
		return localInstance;
	}

	private BorderPane mainBorder;
	private Scene scene;
	private VBox mainBox;
	private ComboBox<String> whatToShowBox;

	private HBox tableHbox;
	private VBox afterTableHbox;

	private PublisherTableCreator ptc;
	private AuthorTableCreator atc;
	private GenreTableCreator gtc;
	private LanguageTableCreator ltc;
	private BookTableCreator btc;
	private LogbookTableCreator lbtc;

	private Button showBooks;
	private Button searchB;
	private TextField searchTF;
	private String comboValue;

	private TextField issueBookTF;
	private Button issueBookB;
	
	private TextField returnBookTF;
	private Button returnBookB;

	private LibrarianPane(Librarian lib) {

		mainBorder = new BorderPane();
		mainBox = new VBox();
		mainBorder.setCenter(mainBox);

		whatToShowBox = new ComboBox<String>(getOptions());
		mainBox.getChildren().add(whatToShowBox);
		whatToShowBox.setValue("Publisher");
		whatToShowBox.getSelectionModel().selectedItemProperty().addListener(getListener());
		comboValue = whatToShowBox.getValue();

		tableHbox = new HBox();
		ptc = new PublisherTableCreator();
		tableHbox.getChildren().add(ptc.getTable());

		mainBox.getChildren().add(tableHbox);
		afterTableHbox = new VBox();
		mainBox.getChildren().add(afterTableHbox);

		showBooks = new Button("Show Books");
		afterTableHbox.getChildren().add(showBooks);
		
		Label searchL = new Label("Search:");
		searchTF = new TextField();
		searchB = new Button("Search");
		HBox searchBox = new HBox(searchL, searchTF, searchB);
		afterTableHbox.getChildren().add(searchBox);
		searchBox.setSpacing(5);
		
		
		Label issueL = new Label("Customer name:");
		issueBookB = new Button("Issue a Book");
		issueBookTF = new TextField();
		HBox issueHBox = new HBox(issueL, issueBookTF, issueBookB);
		issueHBox.setSpacing(5);
		afterTableHbox.getChildren().add(issueHBox);

		Label returnL = new Label("Customer code:");
		returnBookB = new Button("Return a Book");
		returnBookTF = new TextField();
		HBox returnHBox = new HBox(returnL, returnBookTF, returnBookB);
		returnHBox.setSpacing(5);
		afterTableHbox.getChildren().add(returnHBox);
		
		showBooks.setOnAction(getShowBooksListener());
		searchB.setOnAction(getSearchListener());
		issueBookB.setOnAction(getIssueListener());
		returnBookB.setOnAction(getReturnListener());
		
		scene = new Scene(mainBorder, 600, 800);
		this.setResizable(false);
		this.setScene(scene);
		this.setTitle(lib + "'s panel");

		mainBorder.setPadding(new Insets(5));
		mainBox.setSpacing(10);
		tableHbox.setSpacing(10);
		afterTableHbox.setSpacing(10);

		this.show();

	}

	private void clearTableBoxAndAddNew(TableCreator tc) {
		tableHbox.getChildren().clear();
		tableHbox.getChildren().add(tc.getTable());
	}

	private ChangeListener<String> getListener() {
		ChangeListener<String> listener = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				comboValue = newValue;
				switch (comboValue) {
				case "Publisher":
					ptc = new PublisherTableCreator();
					clearTableBoxAndAddNew(ptc);
					break;
				case "Author":
					atc = new AuthorTableCreator();
					clearTableBoxAndAddNew(atc);
					break;
				case "Language":
					ltc = new LanguageTableCreator();
					clearTableBoxAndAddNew(ltc);
					break;
				case "Genre":
					gtc = new GenreTableCreator();
					clearTableBoxAndAddNew(gtc);
					break;
				case "Book":
					btc = new BookTableCreator(false);
					clearTableBoxAndAddNew(btc);
					break;
				case "Logbook":
					lbtc = new LogbookTableCreator();
					clearTableBoxAndAddNew(lbtc);
					break;
				case "Logbook update":

					break;
				}

			}
		};
		return listener;
	}

	private static ObservableList<String> getOptions() {
		ObservableList<String> options = FXCollections.observableArrayList();
		options.add("Publisher");
		options.add("Author");
		options.add("Language");
		options.add("Genre");
		options.add("Book");
		options.add("Logbook");
		options.add("Logbook update");
		return options;
	}

	private EventHandler<ActionEvent> getShowBooksListener() {
		EventHandler<ActionEvent> listener = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switch (comboValue) {
				case "Publisher":
					BookPublisher publ = ptc.getSelectedPublisher();
					tableHbox.getChildren().clear();
					btc = new BookTableCreator(false);
					tableHbox.getChildren().add(btc.getDataTable(publ));
					whatToShowBox.setValue("...");
					break;
				case "Author":
					BookAuthor author = atc.getSelectedAuthor();
					tableHbox.getChildren().clear();
					btc = new BookTableCreator(false);
					tableHbox.getChildren().add(btc.getDataTable(author));
					whatToShowBox.setValue("...");
					break;
				case "Language":
					Language lang = ltc.getSelectedLanguage();
					tableHbox.getChildren().clear();
					btc = new BookTableCreator(false);
					tableHbox.getChildren().add(btc.getDataTable(lang));
					whatToShowBox.setValue("...");
					break;
				case "Genre":
					System.out.println("gtc is " + gtc);
					BookGenre genre = gtc.getSelectedGenre();
					System.out.println("gemnre is " + genre);
					tableHbox.getChildren().clear();
					btc = new BookTableCreator(false);
					tableHbox.getChildren().add(btc.getDataTable(genre));
					whatToShowBox.setValue("...");
					break;
				}

			}
		};
		return listener;
	}

	private EventHandler<ActionEvent> getSearchListener() {
		EventHandler<ActionEvent> listener = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!searchTF.getText().trim().isEmpty()) {
					switch (comboValue) {
					case "Publisher":
						ptc.getDataTable(searchTF.getText());
						break;
					case "Author":
						atc.getDataTable(searchTF.getText());
						break;
					case "Language":
						ltc.getDataTable(searchTF.getText());
						break;
					case "Genre":
						gtc.getDataTable(searchTF.getText());
						break;
					case "Book":
						btc.getDataTable(searchTF.getText());
						break;
					case "Logbook":

						break;
					case "Logbook update":

						break;
					case "...":

						break;
					}
				}
			}
		};
		return listener;
	}

	private EventHandler<ActionEvent> getIssueListener() {
		EventHandler<ActionEvent> listener = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (btc.getSelectedBook() != null && !issueBookTF.getText().trim().isEmpty()) {
					Random rnd = new Random();
					String code = "";
					for (int i = 0; i < 10; i++) {
						code += "" + rnd.nextInt(9) + "";
					}
					try {
						Librarian lib = LibrarianPane.libr;
						//System.out.println("lib = " + lib);
						String libid = lib == null ? "2" : "(SELECT id from librarian where login = '" + lib.getLogin() + "')";
						//System.out.println("libid = " + libid);
						DBConnector connection = new DBConnector();
						Connection c = connection.getConnection();
						Statement stmt = c.createStatement();
						stmt.executeUpdate(
								"INSERT INTO LOGBOOK(book_id, librarian_id, customer_id, individual_code) VALUES("
										+ btc.getSelectedBook().getId() + "," + libid + ","
														+ "(SELECT create_or_get_customer_id('" + issueBookTF.getText().trim() + "')),"
														+ code + ");");

						stmt.executeUpdate(
								"UPDATE book SET is_taken_flag = 'true' where id = " + btc.getSelectedBook().getId() + ";");
						stmt.close();
						c.commit();
						c.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("ОШИБКА " + e.getClass().getName() + ": " + e.getMessage());
					}
				}

			}
		};

		return listener;
	}
	
	private EventHandler<ActionEvent> getReturnListener() {
		EventHandler<ActionEvent> listener = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (lbtc.getSelectedLogbook() != null || !returnBookTF.getText().trim().isEmpty()) {
					//Logbook logbook = lbtc.getSelectedLogbook();
					try {
						DBConnector connection = new DBConnector();
						Connection c = connection.getConnection();
						Statement stmt = c.createStatement();
						stmt.executeUpdate("UPDATE logbook SET is_returned_flag = 'true' WHERE individual_code = '"
								+ returnBookTF.getText().trim() + "';");
						stmt.executeUpdate(
								"UPDATE book SET is_taken_flag = 'false' WHERE id = (SELECT logbook.book_id from logbook where logbook.individual_code = '" + returnBookTF.getText().trim() +"');");
						stmt.close();
						c.commit();
						c.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("ОШИБКА " + e.getClass().getName() + ": " + e.getMessage());
					}
					lbtc.getTable().setItems(lbtc.getData());
					returnBookTF.clear();
				}

			}
		};

		return listener;
	}

}
