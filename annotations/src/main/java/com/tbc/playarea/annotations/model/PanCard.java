package com.tbc.playarea.annotations.model;

import java.time.LocalDate;

public class PanCard {
	
	private String fullname;
	
	private String fatherName;
	
	private String panNumber;
	
	private String issuedBy;
	
	private LocalDate dob;

	public String getFullname() {
		return fullname;
	}

	public String getFatherName() {
		return fatherName;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public LocalDate getDob() {
		return dob;
	}

	private PanCard(PanCardBuilder builder) {
		super();
		this.fullname = builder.fullname;
		this.fatherName = builder.fatherName;
		this.panNumber = builder.panNumber;
		this.issuedBy = builder.issuedBy;
		this.dob = builder.dob;
	}

	@Override
	public String toString() {
		return "PanCard [fullname=" + fullname + ", fatherName=" + fatherName + ", panNumber=" + panNumber
				+ ", issuedBy=" + issuedBy + ", dob=" + dob + "]";
	}

	public static class PanCardBuilder {
		private String fullname;
		
		private String fatherName;
		
		private String panNumber;
		
		private String issuedBy;
		
		private LocalDate dob;

		public PanCardBuilder setFatherName(String fatherName) {
			this.fatherName = fatherName;
			return this;
		}

		public PanCardBuilder setIssuedBy(String issuedBy) {
			this.issuedBy = issuedBy;
			return this;
		}

		public PanCardBuilder setDob(LocalDate dob) {
			this.dob = dob;
			return this;
		}

		public PanCardBuilder(String fullname, String panNumber) {
			super();
			this.fullname = fullname;
			this.panNumber = panNumber;
		}
		
		public PanCard build() {
			return new PanCard(this);
		}
	}
}
