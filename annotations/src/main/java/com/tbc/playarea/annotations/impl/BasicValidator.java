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
		if(null != nextValidator) { 
			boolean nextValid = nextValidator.validate(objects);
			valid = valid ? nextValid : valid;
		}
		return valid;
	}
	
	public String getErrorMessages() {
		StringBuilder sb = new StringBuilder(getErrorReporter().getErrorMessages()).append("\n");
		if(null != nextValidator) {
			sb.append(nextValidator.getErrorMessages()).append("\n");
		}
		return sb.toString();
	}

	protected abstract boolean validateOnMethods(Object... objects);
	protected abstract void setErrorReporter(ErrorReporter errorReporter);
	protected abstract ErrorReporter getErrorReporter();
}
