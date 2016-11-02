package com.veniqs.view;

import com.veniqs.model.Librarian;

import javafx.stage.Stage;

public class LibrarianPane extends Stage {
	private static volatile LibrarianPane instance;

	public static LibrarianPane getInstance(Librarian lib) {
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

	private LibrarianPane(Librarian lib) {
		this.setTitle(lib + "'s pane");
		//scene.getStylesheets().add(this.getClass().getResource("/application/application.css").toExternalForm());
		this.show();
	}
}
