package com.veniqs.view;

import javafx.scene.layout.BorderPane;

public class LibrarianPane extends BorderPane {
	private static volatile LibrarianPane instance;

	public static LibrarianPane getInstance() {
		LibrarianPane localInstance = instance;
		if (localInstance == null) {
			synchronized (LibrarianPane.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new LibrarianPane();
				}
			}
		}
		return localInstance;
	}

	private LibrarianPane() {

	}
}
