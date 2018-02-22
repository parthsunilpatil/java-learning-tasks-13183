package com.tbc.playarea.annotations;

public interface BasicValidationDocument {
	public Object getContent();
	public String getMessage();
	public boolean getValidationStatus();
	public void setMessageAndStatus(String message, boolean validation_status);
}
