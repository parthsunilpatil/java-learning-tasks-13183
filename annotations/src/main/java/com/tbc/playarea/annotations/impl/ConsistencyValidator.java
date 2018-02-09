package com.tbc.playarea.annotations.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.tbc.playarea.annotations.ConsistencyCheck;
import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.model.ValidatingDocument;

public class ConsistencyValidator extends BasicValidator { 

	public ConsistencyValidator(List<String> errorMessages) {
		super(errorMessages);
	}

	@Override
	public boolean validateOnMethods(Object... objects) {
		boolean valid = true;
		for(Object object : objects) {
			Method[] methods = object.getClass().getMethods();
			for(Method method : methods) {
				if(method.isAnnotationPresent(CustomValidateField.class)) {
					try {
						ValidatingDocument document = new ValidatingDocument(method.getAnnotation(ConsistencyCheck.class)
								, method.invoke(object), "Pending Validation");
						valid = (valid) ? basicValidate(document) : false;
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return valid;
		return false;
	}

	private boolean basicValidate(ValidatingDocument document) {
		// TODO Auto-generated method stub
		return false;
	}

}
