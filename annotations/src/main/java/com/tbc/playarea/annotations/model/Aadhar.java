package com.tbc.playarea.annotations.model;

import java.time.LocalDate;

import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.DocumentFields;

public class Aadhar {
	
	private String fullname;
	
	private String gender;
	
	private String address;
	
	private LocalDate dob;

	@CustomValidateField(maxLength = 50, type = DocumentFields.ALPHABETICAL)
	public String getFullname() {
		return fullname;
	}

	@CustomValidateField(type = DocumentFields.GENDER)
	public String getGender() {
		return gender;
	}

	@CustomValidateField(maxLength = 100, type = DocumentFields.ALPHANUMERIC)
	public String getAddress() {
		return address;
	}

	@CustomValidateField(type = DocumentFields.DATE)
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
