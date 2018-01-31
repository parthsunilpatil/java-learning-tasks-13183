/**
 * 
 */
package com.tbc.playarea.javalearning.tasks.refactor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

/**
 * Movie : Simple data class representing movie data.
 * 
 * @author chandrashekarv
 *
 */
public abstract class Book implements Serializable {

	private static final long serialVersionUID = -7348792584072115788L;

	public static final int FICTION = 1;
	public static final int NON_FICTION = 2;
	public static final int CHILDRENS = 3;
	private Date releaseDate;

	private long id;
	private String title;
	private int bookCategory;
	
	private Properties rentalProperties;

	public Book(final String title, final int bookCategory, final Date releaseDate) {
		super();
		this.title = title;
		this.bookCategory = bookCategory;
		this.releaseDate = releaseDate;
		loadProperties("book_rental.properties");
	}

	public Book(final String title, final int bookCategory) {
		super();
		this.title = title;
		this.bookCategory = bookCategory;
		loadProperties("book_rental.properties");
	}
	
	private void loadProperties(String fileName) {
		rentalProperties = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(fileName);
			rentalProperties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public abstract double getAmount(int daysRented);
	public abstract int getPoints(int daysRented);

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(int bookCategory) {
		this.bookCategory = bookCategory;
	}

	public Properties getRentalProperties() {
		return rentalProperties;
	}

}
