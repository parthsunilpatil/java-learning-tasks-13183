package com.tbc.playarea.annotations.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;

import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.DocumentFields;
import com.tbc.playarea.annotations.model.Aadhar;


public class DocumentValidator {
	
	public void handle(Object object) {
		try {
			System.out.println(object.getClass().getName());
			//Field[] fields = object.getClass().getFields();
			Method[] methods = object.getClass().getMethods();
			//for(Field field : fields) {
			for(Method field : methods) {
				if(field.isAnnotationPresent(CustomValidateField.class)) {
					System.out.println(field.getName());
					System.out.println("-----------------------------------------");
					CustomValidateField validateField = field.getAnnotation(CustomValidateField.class);
					System.out.println("Annotation present: " + validateField.toString());
					Object fieldContent = field.invoke(object);
					System.out.println(fieldContent);
					DocumentFields type = validateField.type();
					System.out.println(type);
					if(DocumentFields.ALPHABETICAL.equals(type) || DocumentFields.ALPHANUMERIC.equals(type) || DocumentFields.GENDER.equals(type)) {
						if(!(fieldContent instanceof String)) 
							System.out.println("Field: " + field.getName() + " must be of type String");
						else {
							String str = (String) fieldContent;
							if(DocumentFields.GENDER.equals(type)) {
								if(!"MALE".equalsIgnoreCase(str) && !"FEMALE".equalsIgnoreCase(str) && !"OTHER".equalsIgnoreCase(str))
									System.out.println("Field: " + field.getName() + " must be either Male, Female or Other");
							} else if(str.length() > validateField.minLength() && str.length() < validateField.minLength())
								System.out.println("Field " + field.getName() + " Length must be: " + (validateField.maxLength() - validateField.minLength()) + " but is: " + str.length());
							else if(DocumentFields.ALPHABETICAL.equals(type))
								if(str.contains("[0-9]+"))
									System.out.println("Field: " + field.getName() + " cannot have numerical values.");
						}
					} else if(DocumentFields.DATE.equals(type)) {
						if(!(fieldContent instanceof LocalDate))
							System.out.println("Field: " + field.getName() + " must be of type Date");
						else {
							LocalDate dob = (LocalDate) fieldContent;
							if(dob.isBefore(LocalDate.parse("2018-01-07")) || dob.isAfter(LocalDate.parse("2017-02-07")))
								System.out.println("Date of Birth must be between " + LocalDate.parse("2018-01-07") + " to " + LocalDate.parse("2018-02-07"));
						}
					}
					System.out.println("-----------------------------------------");
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DocumentValidator parser = new DocumentValidator();
		Aadhar aadhar = new Aadhar.AadharBuilder("abc", "abc123").setGender("X").setDob(LocalDate.MIN).build();
		System.out.println(aadhar);
		parser.handle(aadhar);
	}

}
