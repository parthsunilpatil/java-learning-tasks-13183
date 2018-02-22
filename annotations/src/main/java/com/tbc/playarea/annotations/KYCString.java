/**
 * 
 */
package com.tbc.playarea.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
/**
 * @author parthp
 *
 */
public @interface KYCString {
	int minLength() default 1;
	int maxLength() default 100;
	DocumentFields type() default DocumentFields.ALPHANUMERIC;
}
