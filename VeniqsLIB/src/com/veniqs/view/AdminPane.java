package com.veniqs.view;

import javafx.scene.layout.BorderPane;

public class AdminPane extends BorderPane {

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

	}

}
