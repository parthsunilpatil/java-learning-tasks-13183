package com.tbc.playarea.annotations.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.tbc.playarea.annotations.BasicFieldValidation;
import com.tbc.playarea.annotations.DocumentFields;
import com.tbc.playarea.annotations.KYCDate;

public class DateFieldValidator implements BasicFieldValidation {
	
	private ValidationDocument<KYCDate> document;

	public ValidationDocument<KYCDate> getDocument() {
		return document;
	}

	public DateFieldValidator(ValidationDocument<KYCDate> document) {
		super();
		this.document = document;
	}

	@Override
	public boolean validate() {
		KYCDate annotation = document.getFieldAnnotation();
		Object content = document.getContent();
		if(annotation.type().equals(DocumentFields.DATE)) {
			LocalDate dateContent = (LocalDate) content, 
					notBefore = LocalDate.parse(annotation.notBefore()), 
					notAfter = LocalDate.parse(annotation.notAfter());
			
			if(dateContent.isBefore(notBefore) || dateContent.isAfter(notAfter)) {
				document.setMessageAndStatus("Invalid Date!", false);
				return false;
			}
		} else if(annotation.type().equals(DocumentFields.DATE_TIME)) {
			LocalDateTime dateContent = (LocalDateTime) content,
					notBefore = LocalDateTime.parse(annotation.notBefore()),
					notAfter = LocalDateTime.parse(annotation.notAfter());
			if(dateContent.isBefore(notBefore) || dateContent.isAfter(notAfter)) {
				document.setMessageAndStatus("Invalid Date!", false);
				return false;
			}
		}
		
		document.setMessageAndStatus("Validation Passed!", true);
		return true;
	}

}
