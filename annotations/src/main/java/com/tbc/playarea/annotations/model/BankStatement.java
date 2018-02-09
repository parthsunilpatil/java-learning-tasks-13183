package com.tbc.playarea.annotations.model;

import java.util.List;

import com.tbc.playarea.annotations.ConsistencyCheck;
import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.DocumentFields;

public class BankStatement {
	
	private String accountNumber;
	
	private String customerName;
	
	private List<Transaction> transactions;
	
	private String address;
	
	private String mobileNumber;
	
	private String email;

	@CustomValidateField(minLength = 10, maxLength = 10, type = DocumentFields.INTEGER)
	public String getAccountNumber() {
		return accountNumber;
	}

	@ConsistencyCheck
	@CustomValidateField(maxLength = 50, type = DocumentFields.ALPHABETICAL)
	public String getCustomerName() {
		return customerName;
	}

	@CustomValidateField(type = DocumentFields.LIST)
	public List<Transaction> getTransactions() {
		return transactions;
	}

	@ConsistencyCheck
	@CustomValidateField(maxLength = 100, type = DocumentFields.ALPHANUMERIC)
	public String getAddress() {
		return address;
	}

	@CustomValidateField(minLength = 10, maxLength = 10, type = DocumentFields.NUMERIC_STRING)
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	@CustomValidateField(maxLength = 50, type = DocumentFields.EMAIL)
	public String getEmail() {
		return email;
	}

	private BankStatement(BankStatementBuilder builder) {
		super();
		this.accountNumber = builder.accountNumber;
		this.customerName = builder.customerName;
		this.transactions = builder.transactions;
		this.address = builder.address;
		this.mobileNumber = builder.mobileNumber;
		this.email = builder.email;
	}

	public static class BankStatementBuilder {
		private String accountNumber;
		
		private String customerName;
		
		private List<Transaction> transactions;
		
		private String address;
		
		private String mobileNumber;
		
		private String email;

		public BankStatementBuilder(String accountNumber, String customerName, String address) {
			super();
			this.accountNumber = accountNumber;
			this.customerName = customerName;
			this.address = address;
			this.transactions = null;
			this.mobileNumber = null;
			this.email = null;
		}

		public BankStatementBuilder setTransactions(List<Transaction> transactions) {
			this.transactions = transactions;
			return this;
		}

		public BankStatementBuilder setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
			return this;
		}

		public BankStatementBuilder setEmail(String email) {
			return this;
		}
		
		public BankStatement build() {
			return new BankStatement(this);
		}
	}

	@Override
	public String toString() {
		return "BankStatement [accountNumber=" + accountNumber + ", customerName=" + customerName + ", transactions="
				+ transactions + ", address=" + address + ", mobileNumber=" + mobileNumber + ", email=" + email + "]";
	}
}
