package com.veniqs.model;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Logbook {

	private SimpleStringProperty code;
	private SimpleStringProperty bookTitle;
	private SimpleStringProperty customer;
	private SimpleStringProperty librarian;
	private SimpleStringProperty takenDate;
	private SimpleStringProperty expiredDate;
	private SimpleBooleanProperty isReturned;

	public Logbook(String code, String bookTitle, String customer, String librarian, String takenDate,
			String expiredDate, boolean isReturned) {

		this.code = new SimpleStringProperty(code);
		this.bookTitle = new SimpleStringProperty(bookTitle);
		this.customer = new SimpleStringProperty(customer);
		this.librarian = new SimpleStringProperty(librarian);
		this.takenDate = new SimpleStringProperty(takenDate);
		this.expiredDate = new SimpleStringProperty(expiredDate);
		this.isReturned = new SimpleBooleanProperty(isReturned);
	}

	public String getCode() {
		return code.get();
	}

	public String getBookTitle() {
		return bookTitle.get();
	}

	public String getLibrarian() {
		return librarian.get();
	}

	public String getTakenDate() {
		return takenDate.get();
	}

	public String getExpiredDate() {
		return expiredDate.get();
	}

	public boolean getIsReturned() {
		return isReturned.get();
	}

	public String getCustomer() {
		return customer.get();
	}

}
