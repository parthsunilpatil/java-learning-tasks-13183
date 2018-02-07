package com.tbc.playarea.annotations.model;

import java.time.LocalDateTime;

public class Transaction {
	
	private String id;
	
	private String description;
	
	private double amount;
	
	private LocalDateTime transactionTime;

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public double getAmount() {
		return amount;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public Transaction(String id, String description, double amount, LocalDateTime transactionTime) {
		super();
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.transactionTime = transactionTime;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", description=" + description + ", amount=" + amount + ", transactionTime="
				+ transactionTime + "]";
	}

}
