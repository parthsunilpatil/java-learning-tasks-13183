package com.tbc.playarea.generics;

import com.tbc.playarea.generics.service.GenericAnnotatedImplementation;

public enum DaysAnnotated implements GenericAnnotatedImplementation {
	@AnnotatedEnumType("monday") MONDAY,
	@AnnotatedEnumType("tuesday") TUESDAY,
	@AnnotatedEnumType("wednesday") WEDNESDAY,
	@AnnotatedEnumType("thursday") THURSDAY,
	@AnnotatedEnumType("friday") FRIDAY,
	@AnnotatedEnumType("saturday") SATURDAY,
	@AnnotatedEnumType("sunday") SUNDAY;
	
	public static DaysAnnotated fromValue(String value) {
		return GenericAnnotatedImplementation.fromValue(value, DaysAnnotated.class);
	}
}
