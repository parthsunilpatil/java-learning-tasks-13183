package com.tbc.playarea.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface CustomValidateField {
	int minLength() default 1;
	int maxLength() default 100;
	DocumentFields type() default DocumentFields.NONE;
}
