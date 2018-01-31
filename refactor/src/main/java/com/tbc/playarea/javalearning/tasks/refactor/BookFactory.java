package com.tbc.playarea.javalearning.tasks.refactor;

public class BookFactory {
	public Book getBook(String title, int bookCategory) {
		switch(bookCategory) {
		case Book.FICTION:
			return new FictionBook(title);
		case Book.NON_FICTION:
			return new NonFictionBook(title);
		case Book.CHILDRENS:
			return new ChildrenBook(title);
		default:
			System.out.println("Category " + bookCategory + " is not supported please try again");
			System.exit(1);
		}
		return null;
	}
}
