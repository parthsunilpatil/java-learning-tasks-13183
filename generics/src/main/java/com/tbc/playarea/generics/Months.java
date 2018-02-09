package com.tbc.playarea.generics;

import com.tbc.playarea.generics.service.GenericImplementation;

public enum Months implements GenericImplementation {
	JANUARY("january"), FEBRUARY("february"), MARCH("march"), APRIL("april"), MAY("may"), JUNE("june"), JULY("july"), AUGUST("august"), 
	SEPTEMBER("september"), OCTOBER("october"), NOVEMBER("november"), DECEMBER("december");
	
	private final String type;
	private  Months(final String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return this.type;
	}
	
	public static Months fromValue(final String value) {
		return GenericImplementation.fromValue(value, Months.class);
	}

}
