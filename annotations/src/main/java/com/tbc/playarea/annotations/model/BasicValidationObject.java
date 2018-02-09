package com.tbc.playarea.annotations.model;

public abstract class BasicValidationObject {
	
	public abstract Object getContent();
	public abstract String getMessage();
	public abstract boolean getValidationStatus();
	public abstract void setMessageAndStatus(String message, boolean validation_status);
	
	@Override
	public String toString() {
		return "BasicValidationObject [getContent()=" + getContent() + ", getMessage()=" + getMessage()
				+ ", getValidationStatus()=" + getValidationStatus() + "]";
	}

}
