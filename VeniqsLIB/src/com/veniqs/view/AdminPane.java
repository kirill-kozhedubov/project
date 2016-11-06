package com.veniqs.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
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
	private StackPane topPane;
	private StackPane botPane;
	private SplitPane rootSplitPane;

	private AdminPane() {

		root = new BorderPane();
		rootSplitPane = new SplitPane();
		topPane = new StackPane();
		botPane = new StackPane();

		rootSplitPane.setOrientation(Orientation.VERTICAL);
		topPane.setId("showPane");
		botPane.setId("addPane");
		rootSplitPane.getItems().addAll(topPane, botPane);
		rootSplitPane.setDividerPositions(0.5f, 0.5f);
		root.setCenter(rootSplitPane);

		
		
		
		
		// topPane
		VBox topVBox = new VBox();
		ComboBox whatToAddBox = new ComboBox(AdminPane.getOptions());
		Button addButton = new Button("add");
		whatToAddBox.setPromptText("hello");
		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					String st = whatToAddBox.getSelectionModel().getSelectedItem().toString();
					System.out.println(st);
					if (st.equals("Publisher")) System.out.println("XDXDXD");
				} catch (NullPointerException e) {
					System.out.println("null");
				}
			}
		});

		topPane.getChildren().add(topVBox);
		topVBox.getChildren().addAll(whatToAddBox, addButton);
		
		
		
		
		
		
		
		
		
		

		scene = new Scene(root, 1000, 800);
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
		return options;
	}

}
