package com.tbc.playarea.annotations.impl;

import com.tbc.playarea.annotations.BasicFieldValidation;
import com.tbc.playarea.annotations.DocumentFields;
import com.tbc.playarea.annotations.KYCString;

public class StringFieldValidator implements BasicFieldValidation {
	
	private ValidationDocument<KYCString> document;
	
	public StringFieldValidator(ValidationDocument<KYCString> document) {
		super();
		this.document = document;
	}

	public ValidationDocument<KYCString> getDocument() {
		return document;
	}

	@Override
	public boolean validate() {
		KYCString annotation = document.getFieldAnnotation();
		String content = (String) document.getContent();
		if(content != null && (content.length() <= annotation.minLength() || content.length() >= annotation.maxLength())) {
			document.setMessageAndStatus("Content must be at most, " + (annotation.maxLength() - annotation.minLength()) + " long!", false);
			return false;
		}
		if(DocumentFields.ALPHABETICAL.equals(annotation.type()) && !content.matches("[a-zA-Z]+")) {
			document.setMessageAndStatus("Content must not contain numbers or special characters!", false);
			return false;
		}
		if(DocumentFields.NUMERIC_STRING.equals(annotation.type()) && !content.matches("[0-9]+")) {
			document.setMessageAndStatus("Content must contain be a 10 digit number containing digits from 0 to 9!", false);
			return false;
		}
		if(DocumentFields.GENDER.equals(annotation.type()) && !"male".equalsIgnoreCase(content) 
				&& !"female".equalsIgnoreCase(content) && !"other".equalsIgnoreCase(content)) {
			document.setMessageAndStatus("Content must be either male, female or other", false);
			return false;
		}
		document.setMessageAndStatus("Validation Passed!", true);
		return true;
	}	

}
