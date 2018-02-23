package com.tbc.playarea.annotations.impl;

import com.tbc.playarea.annotations.ErrorReporter;

public abstract class BasicValidator {
	protected BasicValidator nextValidator;
	protected ErrorReporter errorReporter;
	
	public BasicValidator() {
		super();
		this.nextValidator = null;
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
	public abstract void setErrorReporter(ErrorReporter errorReporter);
}
