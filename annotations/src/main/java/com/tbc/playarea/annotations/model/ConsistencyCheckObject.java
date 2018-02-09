package com.tbc.playarea.annotations.model;

import com.tbc.playarea.annotations.ConsistencyCheck;

public class ConsistencyCheckObject extends BasicValidationObject {
	private ConsistencyCheck consistencyAnnotation;
	private Object content;
	private String message;
	private boolean validationStatus;

	public ConsistencyCheckObject(ConsistencyCheck consistencyAnnotation, Object content, String message) {
		super();
		this.consistencyAnnotation = consistencyAnnotation;
		this.content = content;
		this.message = message;
	}

	public ConsistencyCheck getConsistencyAnnotation() {
		return consistencyAnnotation;
	}

	@Override
	public Object getContent() {
		// TODO Auto-generated method stub
		return content;
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
	public void setMessageAndStatus(String message, boolean validation_status) {
		// TODO Auto-generated method stub
		this.message = message;
		this.validationStatus = validation_status;
	}

}
