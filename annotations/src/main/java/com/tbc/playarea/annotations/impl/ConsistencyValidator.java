package com.tbc.playarea.annotations.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.tbc.playarea.annotations.BasicValidationDocument;
import com.tbc.playarea.annotations.ConsistencyCheck;
import com.tbc.playarea.annotations.ErrorReporter;

public class ConsistencyValidator extends BasicValidator { 

	public ConsistencyValidator() {
		super();
		ConsistencyValidationErrorReporter errorReporter = new ConsistencyValidationErrorReporter();
		setErrorReporter(errorReporter);
	}

	@Override
	public boolean validateOnMethods(Object... objects) {
		boolean valid = true;
		BasicValidationDocument obj = null, lastObj = null;
		for(Object object : objects) {
			Method[] methods = object.getClass().getMethods();
			for(Method method : methods) {
				if(method.isAnnotationPresent(ConsistencyCheck.class)) {
					try {
						obj = new ValidationDocument<ConsistencyCheck>(method.getAnnotation(ConsistencyCheck.class)
								, method.invoke(object), "Pending Validation");
						if(lastObj != null) {
							if(!lastObj.getContent().equals(obj.getContent())) {
								obj.setMessageAndStatus("Content in previous Object: " + lastObj + " is not Consistent with current object" + obj, false);
								errorReporter.addErrorMessage(obj);
								valid = false;
							}
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				lastObj = obj;
			}
		}
		return valid;
	}

	@Override
	public void setErrorReporter(ErrorReporter errorReporter) {
		super.errorReporter = errorReporter;
	}
}
