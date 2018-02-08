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

	public abstract boolean validateOnMethods(Object... object);
}
