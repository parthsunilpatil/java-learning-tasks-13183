package com.tbc.playarea.annotations;

import java.util.ArrayList;
import java.util.List;

import com.tbc.playarea.annotations.impl.BasicFieldValidator;
import com.tbc.playarea.annotations.impl.BasicValidator;
import com.tbc.playarea.annotations.impl.ConsistencyValidator;
import com.tbc.playarea.annotations.model.Aadhar;
import com.tbc.playarea.annotations.model.BankStatement;
import com.tbc.playarea.annotations.model.PanCard;

public class KYCValidationDriver {
	
	public static boolean validateKYC(Aadhar aadhar, PanCard panCard, BankStatement bankStatement) {
		List<String> errorMessages = new ArrayList<>();
		BasicValidator fieldValidator = new BasicFieldValidator(errorMessages);
		BasicValidator consistencyValidator = new ConsistencyValidator(errorMessages);
		boolean valid = false;
		if((valid = fieldValidator.validateOnMethods(aadhar, panCard, bankStatement)))
			valid = consistencyValidator.validateOnMethods(aadhar, panCard, bankStatement);
		return valid;
	}
}
