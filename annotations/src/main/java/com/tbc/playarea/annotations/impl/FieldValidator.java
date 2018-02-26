package com.tbc.playarea.annotations.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.tbc.playarea.annotations.BasicFieldValidation;
import com.tbc.playarea.annotations.BasicValidationDocument;
import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.ErrorReporter;

public class FieldValidator extends BasicValidator {
	
	@Override
	public boolean validateOnMethods(Object... objects) {
		boolean valid = true;
		for(Object object : objects) {
			Method[] methods = object.getClass().getMethods();
			for(Method method : methods) {
				if(method.isAnnotationPresent(CustomValidateField.class)) {
					try {
						CustomValidateField annotation = method.getAnnotation(CustomValidateField.class);
						BasicValidationDocument document = new ValidationDocument<>(method.getAnnotation(annotation.annotationClass()), 
								method.invoke(object), "Pending Validation");
						Constructor<?> constructor = annotation.validationClass().getConstructor(BasicValidationDocument.class);
						BasicFieldValidation validator = (BasicFieldValidation) constructor.newInstance(document);
						if(!validator.validate()) {
							errorReporter.addErrorMessage(document);
							valid = false;
						}
						
					} catch (IllegalAccessException 
							| IllegalArgumentException 
							| InvocationTargetException 
							| NoSuchMethodException 
							| SecurityException 
							| InstantiationException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return valid;
	}

	public FieldValidator() {
		super();
		FieldValidationErrorReporter errorReporter = new FieldValidationErrorReporter();
		setErrorReporter(errorReporter);
	}

	@Override
	public void setErrorReporter(ErrorReporter errorReporter) {
		super.errorReporter = errorReporter;
	}

	@Override
	public ErrorReporter getErrorReporter() {
		// TODO Auto-generated method stub
		return errorReporter;
	}

}
