package com.veniqs.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {


	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", language=" + language + ", publisher=" + publisher
				+ ", publicationDate=" + publicationDate + ", isTaken=" + isTaken + "]";
	}

	private SimpleIntegerProperty id;
	private SimpleStringProperty title;
	private SimpleStringProperty language;
	private SimpleStringProperty publisher;
	private SimpleIntegerProperty publicationDate;
	private SimpleBooleanProperty isTaken;

	public Book(int idC, String titleC, String languageIDC, String publisherIDC, int publicationDateC, boolean isTakenC) {
		id = new SimpleIntegerProperty(idC);
		title = new SimpleStringProperty(titleC);
		language = new SimpleStringProperty(languageIDC);
		publisher = new SimpleStringProperty(publisherIDC);
		publicationDate = new SimpleIntegerProperty(publicationDateC);
		isTaken = new SimpleBooleanProperty(isTakenC);
	}

	public int getId() {
		return id.get();
	}

	public String getTitle() {
		return title.get();
	}

	public String getLanguage() {
		return language.get();
	}

	public String getPublisher() {
		return publisher.get();
	}

	public int getPublicationDate() {
		return publicationDate.get();
	}
	
	public boolean getIsTaken() {
		return isTaken.get();
	}
	

}
