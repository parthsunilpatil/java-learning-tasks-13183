package com.tbc.playarea.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CustomValidateField {
	Class<? extends Annotation> annotationClass() default CustomValidateField.class;
	Class<? extends BasicFieldValidation> validationClass() default BasicFieldValidation.class;
}
