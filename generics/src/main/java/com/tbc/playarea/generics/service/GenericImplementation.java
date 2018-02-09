package com.tbc.playarea.generics.service;

import java.util.EnumSet;

public interface GenericImplementation {
	String getType();
	static <T extends Enum<T>&GenericImplementation> T fromValue(String value, Class<T> type) {
		for(T t : EnumSet.allOf(type)) {
			if(t.getType().equalsIgnoreCase(value)) {
				return t;
			}
		}
		throw new IllegalArgumentException();
	}
}