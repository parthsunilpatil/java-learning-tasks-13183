package com.tbc.playarea.javalearning.tasks.refactor;

public class EmailStatementStrategy implements StatementStrategy {

	public void exportStatement(Customer customer) {
		System.out.println(":: Exporting Email statement ::\n" + customer.fetchStatement());
	}

}
