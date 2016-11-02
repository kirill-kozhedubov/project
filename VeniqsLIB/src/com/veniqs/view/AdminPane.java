package com.veniqs.view;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
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

		
		
		
		
		
		
		
		
		scene = new Scene(root, 1000, 800);
		this.setResizable(false);
		this.setScene(scene);
		this.setTitle("Admin pane");
		scene.getStylesheets().add(this.getClass().getResource("/application/application.css").toExternalForm());
		
		this.show();
	}

}
