package com.tbc.playarea.generics;

import com.tbc.playarea.generics.service.GenericAnnotatedImplementation;

public enum MonthsAnnotated implements GenericAnnotatedImplementation {
	@AnnotatedEnumType("january") JANUARY,
	@AnnotatedEnumType("february") FEBRUARY,
	@AnnotatedEnumType("march") MARCH,
	@AnnotatedEnumType("april") APRIL,
	@AnnotatedEnumType("may") MAY,
	@AnnotatedEnumType("june") JUNE,
	@AnnotatedEnumType("july") JULY,
	@AnnotatedEnumType("august") AUGUST,
	@AnnotatedEnumType("september") SEPTEMBER,
	@AnnotatedEnumType("october") OCTOBER,
	@AnnotatedEnumType("november") NOVEMBER,
	@AnnotatedEnumType("december") DECEMBER;
	
	public static MonthsAnnotated fromValue(String value) {
		return MonthsAnnotated.fromValue(value);
	}
}
