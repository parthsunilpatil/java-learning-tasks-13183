package com.tbc.playarea.javalearning.tasks.refactor;

public class PDFStatementStrategy implements StatementStrategy {

	public void exportStatement(Customer customer) {
		System.out.println(":: Exporting PDF statement ::\n" + customer.fetchStatement());
	}

}
