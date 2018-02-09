package com.tbc.playarea.annotations.impl;

import java.util.List;

public abstract class BasicValidator {
	protected List<String> errorMessages;
	
	public BasicValidator(List<String> errorMessages) {
		super();
		this.errorMessages = errorMessages;
	}

	public abstract boolean validateOnMethods(Object... objects);
}
