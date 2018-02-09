package com.tbc.playarea.generics;

import com.tbc.playarea.generics.service.GenericImplementation;

public enum Days implements GenericImplementation {
	MONDAY("monday"), TUESDAY("tuesday"), WEDNESDAY("wednesday"), THURSDAY("thursday"), FRIDAY("friday"), SATURDAY("saturday"), SUNDAY("sunday");

	private final String type;
	
	private Days(final String type) {
		this.type = type;
	}
	
	@Override
	public String getType() {
		return this.type;
	}
	
	public static Days fromValue(final String value) {
		return GenericImplementation.fromValue(value, Days.class);
	}

}
