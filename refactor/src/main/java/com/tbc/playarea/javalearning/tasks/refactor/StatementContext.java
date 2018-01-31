package com.tbc.playarea.javalearning.tasks.refactor;

public class StatementContext {
	private StatementStrategy strategy;

	public StatementStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(StatementStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void sendStatement(Customer customer) {
		strategy.exportStatement(customer);
	}
}
