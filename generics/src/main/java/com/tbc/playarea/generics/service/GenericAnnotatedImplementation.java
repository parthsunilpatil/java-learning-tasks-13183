package com.tbc.playarea.generics.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.EnumSet;

public interface GenericAnnotatedImplementation {
	@Retention(RetentionPolicy.RUNTIME) @interface AnnotatedEnumType {
		String value();
	}
	
	default String getType() {
		for(Field f : this.getClass().getDeclaredFields()) {
			try {
				if(f.isEnumConstant() && f.get(this) == null) {
					return f.getAnnotation(AnnotatedEnumType.class).value();
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		throw new IllegalStateException();
	}
	
	static <T extends Enum<T>&GenericAnnotatedImplementation> T fromValue(String value, Class<T> type) {
		for(T t : EnumSet.allOf(type)) {
			if(t.getType().equalsIgnoreCase(value)) {
				return t;
			}
		}
		throw new IllegalArgumentException();
	}
}
