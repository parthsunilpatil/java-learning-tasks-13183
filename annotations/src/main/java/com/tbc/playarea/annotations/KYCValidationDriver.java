package com.tbc.playarea.annotations;

import com.tbc.playarea.annotations.impl.BasicValidator;
import com.tbc.playarea.annotations.impl.ConsistencyValidator;
import com.tbc.playarea.annotations.impl.FieldValidator;
import com.tbc.playarea.annotations.model.Aadhar;
import com.tbc.playarea.annotations.model.BankStatement;
import com.tbc.playarea.annotations.model.PanCard;

public class KYCValidationDriver {
	
	public static boolean validateKYC(Aadhar aadhar, PanCard panCard, BankStatement bankStatement) {
		BasicValidator fieldValidator = new FieldValidator();
		BasicValidator consistencyValidator = new ConsistencyValidator();
		fieldValidator.setNextValidator(consistencyValidator);
		boolean valid = fieldValidator.validate(aadhar, panCard, bankStatement);
		if(!valid) 
			System.out.println(fieldValidator.getErrorMessages());
		return valid;
	}
}
