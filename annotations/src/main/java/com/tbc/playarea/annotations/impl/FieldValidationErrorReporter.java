package com.tbc.playarea.annotations.impl;

import com.tbc.playarea.annotations.BasicValidationDocument;
import com.tbc.playarea.annotations.ErrorReporter;

public class FieldValidationErrorReporter implements ErrorReporter {
	
	private StringBuilder errorMessages;

	public FieldValidationErrorReporter() {
		super();
		errorMessages = new StringBuilder("Field Validation Errors:").append("\n");
	}

	@Override
	public void addErrorMessage(BasicValidationDocument document) {
		if(!document.getValidationStatus()) {
			errorMessages.append(document.toString()).append("\n");
		}
	}

	@Override
	public String getErrorMessages() {
		return errorMessages.toString();
	}

}
