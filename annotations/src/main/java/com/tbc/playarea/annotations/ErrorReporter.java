package com.tbc.playarea.annotations;

public interface ErrorReporter {
	public void addErrorMessage(BasicValidationDocument documents);
	public String getErrorMessages();
}
