package com.tbc.playarea.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface KYCRealNumber {
	double min() default Double.MIN_VALUE;
	double max() default Double.MAX_VALUE;
}
