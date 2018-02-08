package com.tbc.playarea.annotations.model;

import java.time.LocalDateTime;

import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.DocumentFields;

public class Transaction {
	
	private String id;
	
	private String description;
	
	private double amount;
	
	private LocalDateTime transactionTime;

	@CustomValidateField(minLength = 15, maxLength = 15, type = DocumentFields.ALPHANUMERIC)
	public String getId() {
		return id;
	}

	@CustomValidateField(type = DocumentFields.ALPHABETICAL)
	public String getDescription() {
		return description;
	}

	@CustomValidateField(type = DocumentFields.REAL_NUMBER)
	public double getAmount() {
		return amount;
	}

	@CustomValidateField(type = DocumentFields.DATE)
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
