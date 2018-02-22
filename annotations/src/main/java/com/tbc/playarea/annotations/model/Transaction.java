package com.tbc.playarea.annotations.model;

import java.time.LocalDateTime;

import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.DocumentFields;
import com.tbc.playarea.annotations.KYCDate;
import com.tbc.playarea.annotations.KYCRealNumber;
import com.tbc.playarea.annotations.KYCString;

public class Transaction {
	
	private String id;
	
	private String description;
	
	private double amount;
	
	private LocalDateTime transactionTime;

	@CustomValidateField(minLength = 15, maxLength = 15, type = DocumentFields.ALPHANUMERIC)
	@KYCString(minLength = 15, maxLength = 15)
	public String getId() {
		return id;
	}

	@CustomValidateField(type = DocumentFields.ALPHABETICAL)
	@KYCString(type = DocumentFields.ALPHABETICAL)
	public String getDescription() {
		return description;
	}

	@CustomValidateField(type = DocumentFields.REAL_NUMBER)
	@KYCRealNumber
	public double getAmount() {
		return amount;
	}

	@CustomValidateField(type = DocumentFields.DATE)
	@KYCDate(notBefore = "1969-01-01T00:00:00.000", notAfter = "2018-02-21T23:59:59.999", type = DocumentFields.DATE_TIME)
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
