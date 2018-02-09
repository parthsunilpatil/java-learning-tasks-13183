package com.tbc.playarea.annotations.model;

import com.tbc.playarea.annotations.CustomValidateField;

public class FieldValidationObject extends BasicValidationObject {
	private CustomValidateField fieldAnnotation;
	private Object content;
	private String message;
	private boolean validationStatus;
	
	public FieldValidationObject(CustomValidateField fieldAnnotation, Object content, String message) {
		super();
		this.fieldAnnotation = fieldAnnotation;
		this.content = content;
		this.message = message;
		this.validationStatus = false;
	}

	public CustomValidateField getFieldAnnotation() {
		return fieldAnnotation;
	}
	
	@Override
	public void setMessageAndStatus(String message, boolean validation_status) {
		this.message = message;
		this.validationStatus = validation_status;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	@Override
	public boolean getValidationStatus() {
		// TODO Auto-generated method stub
		return validationStatus;
	}
	@Override
	public Object getContent() {
		// TODO Auto-generated method stub
		return content;
	}
}
