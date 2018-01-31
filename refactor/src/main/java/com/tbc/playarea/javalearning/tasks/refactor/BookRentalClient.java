package com.tbc.playarea.javalearning.tasks.refactor;

public class BookRentalClient {
	public static void main(String[] args) {
		BookFactory bookFactory = new BookFactory();
		Customer customer = new Customer("customer_1");
		customer.addRental(new Rental(bookFactory.getBook("fiction_1", Book.FICTION), 1));
		customer.addRental(new Rental(bookFactory.getBook("fiction_2", Book.FICTION), 2));
		customer.addRental(new Rental(bookFactory.getBook("fiction_3", Book.FICTION), 3));
		customer.addRental(new Rental(bookFactory.getBook("fiction_4", Book.FICTION), 4));
		customer.addRental(new Rental(bookFactory.getBook("non_fiction_1", Book.NON_FICTION), 1));
		customer.addRental(new Rental(bookFactory.getBook("non_fiction_2", Book.NON_FICTION), 2));
		customer.addRental(new Rental(bookFactory.getBook("non_fiction_3", Book.NON_FICTION), 3));
		customer.addRental(new Rental(bookFactory.getBook("non_fiction_4", Book.NON_FICTION), 4));
		customer.addRental(new Rental(bookFactory.getBook("children_1", Book.CHILDRENS), 1));
		customer.addRental(new Rental(bookFactory.getBook("children_2", Book.CHILDRENS), 2));
		customer.addRental(new Rental(bookFactory.getBook("children_3", Book.CHILDRENS), 3));
		customer.addRental(new Rental(bookFactory.getBook("children_4", Book.CHILDRENS), 4));
		
		StatementContext context = new StatementContext();
		context.setStrategy(new PDFStatementStrategy());
		context.sendStatement(customer);
		
		context.setStrategy(new EmailStatementStrategy());
		context.sendStatement(customer);
	}
}
