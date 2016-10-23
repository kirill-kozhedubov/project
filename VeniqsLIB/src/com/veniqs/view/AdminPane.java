package com.veniqs.view;

import javafx.scene.layout.BorderPane;
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
		}
		return localInstance;
	}

	private AdminPane() {
		this.setTitle("xd");
		this.show();
	}

}
