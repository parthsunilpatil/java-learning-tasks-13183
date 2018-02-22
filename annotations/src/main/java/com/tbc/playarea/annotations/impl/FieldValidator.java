package com.tbc.playarea.annotations.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.KYCDate;
import com.tbc.playarea.annotations.KYCList;
import com.tbc.playarea.annotations.KYCNumber;
import com.tbc.playarea.annotations.KYCRealNumber;
import com.tbc.playarea.annotations.KYCString;

public class FieldValidator extends BasicValidator {
	
	@Override
	public boolean validateOnMethods(Object... objects) {
		boolean valid = true;
		for(Object object : objects) {
			Method[] methods = object.getClass().getMethods();
			for(Method method : methods) {
				if(method.isAnnotationPresent(CustomValidateField.class)) {
					try {
						if(method.isAnnotationPresent(KYCString.class)) {
							ValidationDocument<KYCString> document = new ValidationDocument<KYCString>(method.getAnnotation(KYCString.class), 
									method.invoke(object), "Pending Validation");
							StringFieldValidator validator = new StringFieldValidator(document);
							if(!validator.validate()) {
								errorMessages.add(document.toString());
								valid = false;
							}
						} else if(method.isAnnotationPresent(KYCDate.class)) {
							ValidationDocument<KYCDate> document = new ValidationDocument<KYCDate>(method.getAnnotation(KYCDate.class),
									method.invoke(object), "Pending Validation");
							DateFieldValidator validator = new DateFieldValidator(document);
							if(!validator.validate()) {
								errorMessages.add(document.toString());
								valid = false;
							}
						} else if(method.isAnnotationPresent(KYCNumber.class)) {
							ValidationDocument<KYCNumber> document = new ValidationDocument<KYCNumber>(method.getAnnotation(KYCNumber.class),
									method.invoke(object), "Pending Validation");
							NumberFieldValidator validator = new NumberFieldValidator(document);
							if(!validator.validate()) {
								errorMessages.add(document.toString());
								valid = false;
							}
						} else if(method.isAnnotationPresent(KYCRealNumber.class)) {
							ValidationDocument<KYCRealNumber> document = new ValidationDocument<KYCRealNumber>(method.getAnnotation(KYCRealNumber.class),
									method.invoke(object), "Pending Validation");
							RealNumberFieldValidator validator = new RealNumberFieldValidator(document);
							if(!validator.validate()) {
								errorMessages.add(document.toString());
								valid = false;
							}
						} else if(method.isAnnotationPresent(KYCList.class)) {
							@SuppressWarnings("unchecked")
							List<Object> listContent = (List<Object>) method.invoke(object);
							for(Object content : listContent) 
								valid = validateOnMethods(content);
						}
						
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return valid;
	}

	public FieldValidator(List<String> errorMessages) {
		super(errorMessages);
	}

}
