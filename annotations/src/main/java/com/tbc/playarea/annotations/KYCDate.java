package com.tbc.playarea.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface KYCDate {
	String notBefore() default "-999999999-01-01";
	String notAfter() default "999999999-12-31";
	DocumentFields type() default DocumentFields.DATE;
}
