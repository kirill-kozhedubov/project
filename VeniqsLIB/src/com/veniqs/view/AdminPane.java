package com.veniqs.view;

import com.veniqs.controller.admin.WhatToAddHandler;
import com.veniqs.model.BookPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminPane extends Stage {

	private static volatile AdminPane instance;

	public static AdminPane getInstance() {
		AdminPane localInstance = instance;
		if (localInstance == null) {
			synchronized (AdminPane.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new AdminPane();
				}
			}
		} else {
			instance.show();
		}
		return localInstance;
	}

	private BorderPane root;
	private Scene scene;
	private SplitPane rootSplitPane;

	// top
	private StackPane topPane;
	private VBox underTopPane;
	private ComboBox<String> whatToAddBox;

	// bot
	private StackPane botPane;
	private TableView tv;
	HBox botHBox;

	private AdminPane() {

		root = new BorderPane();
		rootSplitPane = new SplitPane();
		topPane = new StackPane();
		underTopPane = new VBox();
		botPane = new StackPane();

		rootSplitPane.setOrientation(Orientation.VERTICAL);
		topPane.setId("showPane");
		botPane.setId("addPane");
		rootSplitPane.getItems().addAll(topPane, botPane);
		rootSplitPane.setDividerPositions(0.5f, 0.5f);
		root.setCenter(rootSplitPane);

		// topPane
		VBox topVBox = new VBox();
		whatToAddBox = new ComboBox(AdminPane.getOptions());
		whatToAddBox.setPromptText("Chose...");
		topVBox.getChildren().addAll(whatToAddBox, underTopPane);
		topPane.getChildren().addAll(topVBox);
		topPane.setPadding(new Insets(10));

		// bot pane
		botHBox = new HBox();
		botPane.getChildren().add(botHBox);

		WhatToAddHandler whatToAddHandler = new WhatToAddHandler(whatToAddBox, underTopPane, botHBox);
		whatToAddBox.getSelectionModel().selectedItemProperty().addListener(whatToAddHandler);

		scene = new Scene(root, 500, 800);
		this.setResizable(false);
		this.setScene(scene);
		this.setTitle("Admin pane");
		scene.getStylesheets().add(this.getClass().getResource("/application/application.css").toExternalForm());
		this.show();
	}

	private static ObservableList<String> getOptions() {
		ObservableList<String> options = FXCollections.observableArrayList();
		options.add("Publisher");
		options.add("Author");
		options.add("Language");
		options.add("Genre");
		options.add("Customer");
		options.add("Book");
		options.add("Librarian");
		options.add("Logbook");
		options.add("Logbook update");
		return options;
	}

	public ComboBox<String> getAddComboBox() {
		return whatToAddBox;
	}

	public VBox getTopPane() {
		return underTopPane;
	}

}
