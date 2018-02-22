package com.tbc.playarea.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface KYCNumber {
	int min() default Integer.MIN_VALUE;
	int max() default Integer.MAX_VALUE;
}
