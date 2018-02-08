package com.tbc.playarea.annotations.model;

import com.tbc.playarea.annotations.CustomValidateField;

public class ValidatingDocument {
	private CustomValidateField fieldAnnotation;
	private Object content;
	private String message;
	private boolean validation_status;
	public ValidatingDocument(CustomValidateField fieldAnnotation, Object content, String message) {
		super();
		this.fieldAnnotation = fieldAnnotation;
		this.content = content;
		this.message = message;
		this.setValidation_status(false);
	}
	public CustomValidateField getFieldAnnotation() {
		return fieldAnnotation;
	}
	public Object getContent() {
		return content;
	}
	public String getmessage() {
		return message;
	}
	public void setMessageAndStatus(String message, boolean validation_status) {
		this.message = message;
		this.validation_status = validation_status;
	}
	public boolean isValidation_status() {
		return validation_status;
	}
	public void setValidation_status(boolean validation_status) {
		this.validation_status = validation_status;
	}
	@Override
	public String toString() {
		return "ValidatingDocument [fieldAnnotation=" + fieldAnnotation + ", content=" + content + ", message="
				+ message + ", validation_status=" + validation_status + "]";
	}
}
