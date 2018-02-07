package com.tbc.playarea.annotations.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.DocumentFields;
import com.tbc.playarea.annotations.model.ValidatingDocument;

public abstract class BasicFieldValidator {
	protected Map<Method, ValidatingDocument> methodsMap;
	
	public void addMethodsToValidate(Object object) {
		Method[] methods = object.getClass().getMethods();
		for(Method method : methods) {
			if(method.isAnnotationPresent(CustomValidateField.class)) {
				try {
					methodsMap.put(method, new ValidatingDocument(method.getAnnotation(CustomValidateField.class), method.invoke(object), "Pending"));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean validateBasic() {
		for(Entry<Method, ValidatingDocument> entry : methodsMap.entrySet()) {
			CustomValidateField annotation = entry.getValue().getFieldAnnotation();
			Object content = entry.getValue().getContent();
			if(isStringType(annotation.type())) {
				if(!(content instanceof String)) {
					methodsMap.put(entry.getKey(), entry.getValue().setMessage("Content must be of type String!"));
					return false;
				}
				String strContent = (String) content;
				if(!checkLengthConstraints(annotation, strContent)) {
					methodsMap.put(entry.getKey(), entry.getValue().setMessage("Content must be at most, " + (annotation.maxLength() - annotation.minLength()) + " long!"));
					return false;
				}
				if(DocumentFields.ALPHABETICAL.equals(annotation.type()) && !strContent.matches("[a-zA-Z]+")) {
					methodsMap.put(entry.getKey(), entry.getValue().setMessage("Content must not contain numbers or special characters!"));
					return false;
				}
			}
			if(isIntegerType(annotation.type())) {
				if(!(content instanceof Integer)) {
					methodsMap.put(entry.getKey(), entry.getValue().setMessage("Content must be of type Integer!"));
					return false;
				}
			}
			if(isFloatingType(annotation.type())) {
				if(!(content instanceof Double)) {
					methodsMap.put(entry.getKey(), entry.getValue().setMessage("Content must be of type Double!"));
					return false;
				}
			}
		}
		return true;
	}

	protected boolean checkLengthConstraints(CustomValidateField annotation, String strContent) {
		// TODO Auto-generated method stub
		return strContent.length() > annotation.maxLength() || strContent.length() < annotation.minLength();
	}

	protected boolean isStringType(DocumentFields type) {
		// TODO Auto-generated method stub
		return DocumentFields.ALPHABETICAL.equals(type) 
				|| DocumentFields.ALPHANUMERIC.equals(type) 
				|| DocumentFields.GENDER.equals(type)
				|| DocumentFields.EMAIL.equals(type);
	}
	
	protected boolean isIntegerType(DocumentFields type) {
		return DocumentFields.INTEGER.equals(type);
	}
	
	protected boolean isFloatingType(DocumentFields type) {
		return DocumentFields.REAL_NUMBER.equals(type);
	}
	
	protected boolean isDateType(DocumentFields type) {
		return DocumentFields.DATE.equals(type);
	}

}
