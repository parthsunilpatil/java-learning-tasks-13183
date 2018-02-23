package com.tbc.playarea.annotations.impl;

import com.tbc.playarea.annotations.BasicValidationDocument;
import com.tbc.playarea.annotations.ErrorReporter;

public class ConsistencyValidationErrorReporter implements ErrorReporter {
	
	private StringBuilder errorMessages;

	public ConsistencyValidationErrorReporter() {
		super();
		errorMessages = new StringBuilder("Consistency Check errors:").append("\n");
	}

	@Override
	public void addErrorMessage(BasicValidationDocument document) {
		errorMessages.append(document.toString()).append("\n");
	}

	@Override
	public String getErrorMessages() {
		return errorMessages.toString();
	}

}
