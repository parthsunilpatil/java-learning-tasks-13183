package com.tbc.playarea.annotations.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;

import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.DocumentFields;
import com.tbc.playarea.annotations.model.ValidatingDocument;

public class BasicFieldValidator extends BasicValidator {
	
	@Override
	public boolean validateOnMethods(Object... objects) {
		boolean valid = true;
		for(Object object : objects) {
			Method[] methods = object.getClass().getMethods();
			for(Method method : methods) {
				if(method.isAnnotationPresent(CustomValidateField.class)) {
					try {
						ValidatingDocument document = new ValidatingDocument(method.getAnnotation(CustomValidateField.class)
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
	}
	
	protected boolean isListType(DocumentFields type) {
		// TODO Auto-generated method stub
		return DocumentFields.LIST.equals(type);
	}

	protected boolean checkDateConstraints(CustomValidateField annotation, LocalDate dateContent) {
		// TODO Auto-generated method stub
		return dateContent.isAfter(LocalDate.MIN) && dateContent.isBefore(LocalDate.MAX);
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
	
	private boolean basicValidate(ValidatingDocument document) {

		CustomValidateField annotation = document.getFieldAnnotation();
		Object content = document.getContent();
		if(isStringType(annotation.type())) {
			if(!(content instanceof String)) {
				document.setMessageAndStatus("Content must be of type String!", false);
				errorMessages.add(document.toString());
				return false;
			}
			String strContent = (String) content;
			if(!checkLengthConstraints(annotation, strContent)) {
				document.setMessageAndStatus("Content must be at most, " + (annotation.maxLength() - annotation.minLength()) + " long!", false);
				errorMessages.add(document.toString());
				return false;
			}
			if(DocumentFields.ALPHABETICAL.equals(annotation.type()) && !strContent.matches("[a-zA-Z]+")) {
				document.setMessageAndStatus("Content must not contain numbers or special characters!", false);
				errorMessages.add(document.toString());
				return false;
			}
		}
		if(isIntegerType(annotation.type())) {
			if(!(content instanceof Integer)) {
				document.setMessageAndStatus("Content must be of type Integer!", false);
				errorMessages.add(document.toString());
				return false;
			}
		}
		if(isFloatingType(annotation.type())) {
			if(!(content instanceof Double)) {
				document.setMessageAndStatus("Content must be of type Double!", false);
				errorMessages.add(document.toString());
				return false;
			}
		}
		if(isListType(annotation.type())) {
			if(!(content instanceof List)) {
				document.setMessageAndStatus("Content must be of type List!", false);
				errorMessages.add(document.toString());
				return false;
			}
			
			@SuppressWarnings("unchecked")
			List<Object> listContent = (List<Object>) content;
			for(Object object : listContent) 
				validateOnMethods(object);
		}
		if(isDateType(annotation.type())) {
			if(!(content instanceof LocalDate)) {
				document.setMessageAndStatus("Content must be of type LocalDate!", false);
				errorMessages.add(document.toString());
				return false;
			}
			LocalDate dateContent = (LocalDate) content;
			if(!checkDateConstraints(annotation, dateContent)) {
				document.setMessageAndStatus("Invalid Date!", false);
				errorMessages.add(document.toString());
				return false;
			}
		}
		document.setMessageAndStatus("Validation Passed!", true);
		return true;
	}

	public BasicFieldValidator(List<String> errorMessages) {
		super(errorMessages);
	}

}
