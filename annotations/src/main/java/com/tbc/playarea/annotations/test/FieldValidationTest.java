package com.tbc.playarea.annotations.test;

import java.lang.reflect.InvocationTargetException;

import com.tbc.playarea.annotations.ConsistencyCheck;
import com.tbc.playarea.annotations.CustomValidateField;
import com.tbc.playarea.annotations.DocumentFields;
import com.tbc.playarea.annotations.KYCString;
import com.tbc.playarea.annotations.impl.BasicValidator;
import com.tbc.playarea.annotations.impl.ConsistencyValidator;
import com.tbc.playarea.annotations.impl.FieldValidator;
import com.tbc.playarea.annotations.impl.StringFieldValidator;

public class FieldValidationTest {
	private String str;

	public FieldValidationTest(String str) {
		super();
		this.str = str;
	}

	@CustomValidateField(annotationClass = KYCString.class, validationClass = StringFieldValidator.class)
	@ConsistencyCheck
	@KYCString(type = DocumentFields.ALPHANUMERIC)
	public String getStr() {
		return str;
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		FieldValidationTest test1 = new FieldValidationTest("abc123");
		FieldValidationTest test2 = new FieldValidationTest("xyz123");
		BasicValidator fieldValidator = new FieldValidator();
		BasicValidator consistencyValidator = new ConsistencyValidator();
		fieldValidator.setNextValidator(consistencyValidator);
		boolean valid = fieldValidator.validate(test1, test2);
		if(!valid) System.out.println(fieldValidator.getErrorMessages());
		else System.out.println("Validation passed");
	}
}
