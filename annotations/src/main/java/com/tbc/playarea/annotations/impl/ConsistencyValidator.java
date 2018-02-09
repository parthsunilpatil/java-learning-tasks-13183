package com.tbc.playarea.annotations.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.tbc.playarea.annotations.ConsistencyCheck;
import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.model.ConsistencyCheckObject;

public class ConsistencyValidator extends BasicValidator { 

	public ConsistencyValidator(List<String> errorMessages) {
		super(errorMessages);
	}

	@Override
	public boolean validateOnMethods(Object... objects) {
		
		boolean valid = true;
		ConsistencyCheckObject obj = null, lastObj = null;
		for(Object object : objects) {
			Method[] methods = object.getClass().getMethods();
			for(Method method : methods) {
				if(method.isAnnotationPresent(CustomValidateField.class)) {
					try {
						obj = new ConsistencyCheckObject(method.getAnnotation(ConsistencyCheck.class)
								, method.invoke(object), "Pending Validation");
						if(lastObj != null) {
							if(!lastObj.getContent().equals(obj.getContent())) {
								obj.setMessageAndStatus("Content in previous Object: " + lastObj + " is not Consistent with current object" + obj, false);
								errorMessages.add(obj.toString());
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
}
