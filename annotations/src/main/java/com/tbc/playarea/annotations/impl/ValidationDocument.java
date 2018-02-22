package com.tbc.playarea.annotations.impl;

import com.tbc.playarea.annotations.BasicValidationDocument;

public class ValidationDocument<T> implements BasicValidationDocument {
	private T fieldAnnotation;
	private Object content;
	private String message;
	private boolean validationStatus;

	public ValidationDocument(T fieldAnnotation, Object content, String message) {
		super();
		this.fieldAnnotation = fieldAnnotation;
		this.content = content;
		this.message = message;
		this.validationStatus = false;
	}

	public T getFieldAnnotation() {
		return fieldAnnotation;
	}

	public void setFieldAnnotation(T fieldAnnotation) {
		this.fieldAnnotation = fieldAnnotation;
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

	@Override
	public String toString() {
		return "ValidationDocument [fieldAnnotation=" + fieldAnnotation + ", content=" + content + ", message="
				+ message + ", validationStatus=" + validationStatus + "]";
	}

}
