package com.tbc.playarea.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface KYCList {
	int minLength() default 0;
	int maxLength() default Integer.MAX_VALUE - 5;
}
