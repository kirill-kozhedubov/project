package com.veniq.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.veniqs.controller.db.DBConnector;

import application.Main;

public class PublisherGenerator {

	private Random rnd = new Random();
	
	private String[] publisher = { "Coddleswort", "Crumplesack", "Curdlesnoot", "Calldispatch", "Humperdinck",
			"Rivendell", "Cuttlefish", "Lingerie", "Vegemite", "Ampersand", "Cumberbund", "Candycrush", "Clombyclomp",
			"Cragglethatch", "Nottinghill", "Cabbagepatch", "Camouflage", "Creamsicle", "Curdlemilk", "Upperclass",
			"Frumblesnatch", "Crumplehorn", "Talisman", "Candlestick", "Chesterfield", "Bumbersplat", "Scratchnsniff",
			"Snugglesnatch", "Charizard", "Carrotstick", "Cumbercooch", "Crackerjack", "Crucifix", "Cuckatoo",
			"Cockletit", "Collywog", "Capncrunch", "Covergirl", "Cumbersnatch", "Countryside", "Coggleswort",
			"Splishnsplash", "Copperwire", "Animorph", "Curdledmilk", "Cheddarcheese", "Cottagecheese", "Crumplehorn",
			"Snickersbar", "Banglesnatch", "Stinkyrash", "Cameltoe", "Chickenbroth", "Concubine", "Candygram",
			"Moldyspore", "Chuckecheese", "Cankersore", "Crimpysnitch", "Wafflesmack", "Chowderpants", "Toodlesnoot",
			"Clavichord", "Cuckooclock", "Oxfordshire", "Cumbersome", "Chickenstrips", "Battleship", "Commonwealth",
			"Cunningsnatch", "Custardbath", "Kryptonite", "Curdlesnoot", "Cummerbund", "Coochyrash", "Crackerdong",
			"Crackerdong", "Curdledong", "Crackersprout", "Crumplebutt", "Colonist", "Coochierash" };

	private String getPublisherName() {
		return getRandomValue(publisher);
	}

	private String getRandomValue(String[] mas) {
		return mas[rnd.nextInt(mas.length - 1)];
	}
	
	private PublisherGenerator() {
		try {
			for (int i = 0; i < 100; i++) {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			//System.out.println("Opened database successfully AddOneEntityHandler");
			Statement stmt = c.createStatement();
			String query = "SELECT create_or_get_publisher_id('" + getPublisherName() + "');";
			stmt.executeQuery(query);
			/*
			 * System.out.println(query); ResultSet rs =
			 * stmt.executeQuery(query); while (rs.next()) { int id =
			 * rs.getInt(rs.getMetaData().getColumnName(1));
			 * System.out.println(id + " " + rs.getMetaData().getColumnName(1));
			 * } rs.close();
			 */
			stmt.close();
			c.commit();
			c.close();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ОШИБКА publisher " + e.getClass().getName() + ": " + e.getMessage());
		}
		
	}

}
