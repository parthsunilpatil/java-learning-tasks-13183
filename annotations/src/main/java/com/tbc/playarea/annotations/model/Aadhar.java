package com.tbc.playarea.annotations.model;

import java.time.LocalDate;

import com.tbc.playarea.annotations.ConsistencyCheck;
import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.DocumentFields;
import com.tbc.playarea.annotations.KYCDate;
import com.tbc.playarea.annotations.KYCString;
import com.tbc.playarea.annotations.impl.DateFieldValidator;
import com.tbc.playarea.annotations.impl.StringFieldValidator;

public class Aadhar {
	
	private String fullname;
	
	private String gender;
	
	private String address;
	
	private LocalDate dob;

	@ConsistencyCheck
	@CustomValidateField(annotationClass = KYCString.class, validationClass = StringFieldValidator.class)
	@KYCString(maxLength = 50, type = DocumentFields.ALPHABETICAL)
	public String getFullname() {
		return fullname;
	}

	@CustomValidateField(annotationClass = KYCString.class, validationClass = StringFieldValidator.class)
	@KYCString(minLength = 4, maxLength = 6, type = DocumentFields.GENDER)
	public String getGender() {
		return gender;
	}

	@ConsistencyCheck
	@CustomValidateField(annotationClass = KYCString.class, validationClass = StringFieldValidator.class)
	@KYCString(maxLength = 100)
	public String getAddress() {
		return address;
	}
	
	@CustomValidateField(annotationClass = KYCDate.class, validationClass = DateFieldValidator.class)
	@KYCDate(notBefore = "1969-01-01", notAfter = "2018-12-31")
	public LocalDate getDob() {
		return dob;
	}

	private Aadhar(AadharBuilder builder) {
		super();
		this.fullname = builder.fullname;
		this.gender = builder.gender;
		this.address = builder.address;
		this.dob = builder.dob;
	}

	public static class AadharBuilder {
		private String fullname;
		
		private String gender;
		
		private String address;
		
		private LocalDate dob;
		
		public AadharBuilder(String fullname, String address) {
			this.fullname = fullname;
			this.address = address;
			this.gender = null;
			this.dob = null;
		}

		public AadharBuilder setGender(String gender) {
			this.gender = gender;
			return this;
		}

		public AadharBuilder setDob(LocalDate dob) {
			this.dob = dob;
			return this;
		}
		
		public Aadhar build() {
			return new Aadhar(this);
		}
	}

	@Override
	public String toString() {
		return "Aadhar [fullname=" + fullname + ", gender=" + gender + ", address=" + address + ", dob=" + dob + "]";
	}
}
