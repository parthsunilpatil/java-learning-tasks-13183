package com.tbc.playarea.annotations.model;

import com.tbc.playarea.annotations.CustomValidateField;

public class ValidatingDocument {
	private CustomValidateField fieldAnnotation;
	private Object content;
	private String message;
	public ValidatingDocument(CustomValidateField fieldAnnotation, Object content, String message) {
		super();
		this.fieldAnnotation = fieldAnnotation;
		this.content = content;
		this.message = message;
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
	public ValidatingDocument setMessage(String message) {
		this.message = message;
		return this;
	}
}
