package com.tbc.playarea.annotations.impl;

import java.util.List;

public abstract class BasicValidator {
	protected List<String> errorMessages;
	protected BasicValidator nextValidator;
	
	public BasicValidator(List<String> errorMessages) {
		super();
		this.errorMessages = errorMessages;
		this.nextValidator = null;
	}
	
	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setNextValidator(BasicValidator nextValidator) {
		this.nextValidator = nextValidator;
	}
	
	public boolean validate(Object... objects) {
		boolean valid = validateOnMethods(objects);
		if(valid && null != nextValidator) 
			valid = nextValidator.validate(objects);
		return valid;
	}

	public abstract boolean validateOnMethods(Object... objects);
}
