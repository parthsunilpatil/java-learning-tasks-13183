package com.tbc.playarea.annotations.model;

import java.time.LocalDate;

import com.tbc.playarea.annotations.ConsistencyCheck;
import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.DocumentFields;
import com.tbc.playarea.annotations.KYCDate;
import com.tbc.playarea.annotations.KYCString;
import com.tbc.playarea.annotations.impl.DateFieldValidator;
import com.tbc.playarea.annotations.impl.StringFieldValidator;

public class PanCard {
	
	private String fullname;
	
	private String fatherName;
	
	private String panNumber;
	
	private String issuedBy;
	
	private LocalDate dob;

	@ConsistencyCheck
	@CustomValidateField(annotationClass = KYCString.class, validationClass = StringFieldValidator.class)
	@KYCString(maxLength = 50, type = DocumentFields.ALPHABETICAL)
	public String getFullname() {
		return fullname;
	}

	@CustomValidateField(annotationClass = KYCString.class, validationClass = StringFieldValidator.class)
	@KYCString(maxLength = 50, type = DocumentFields.ALPHABETICAL)
	public String getFatherName() {
		return fatherName;
	}

	@CustomValidateField(annotationClass = KYCString.class, validationClass = StringFieldValidator.class)
	@KYCString(maxLength = 10, type = DocumentFields.ALPHANUMERIC)
	public String getPanNumber() {
		return panNumber;
	}

	@CustomValidateField(annotationClass = KYCString.class, validationClass = StringFieldValidator.class)
	@KYCString(maxLength = 50, type = DocumentFields.ALPHABETICAL)
	public String getIssuedBy() {
		return issuedBy;
	}

	@CustomValidateField(annotationClass = KYCDate.class, validationClass = DateFieldValidator.class)
	@KYCDate(notBefore = "1969-01-01", notAfter = "2018-12-31")
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
			this.issuedBy = null;
			this.dob = null;
		}
		
		public PanCard build() {
			return new PanCard(this);
		}
	}
}
