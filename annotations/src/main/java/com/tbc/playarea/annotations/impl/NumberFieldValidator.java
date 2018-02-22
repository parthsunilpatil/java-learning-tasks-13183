package com.tbc.playarea.annotations.impl;

import com.tbc.playarea.annotations.BasicFieldValidation;
import com.tbc.playarea.annotations.KYCNumber;

public class NumberFieldValidator implements BasicFieldValidation {
	private ValidationDocument<KYCNumber> document;

	public ValidationDocument<KYCNumber> getDocument() {
		return document;
	}

	public NumberFieldValidator(ValidationDocument<KYCNumber> document) {
		super();
		this.document = document;
	}

	@Override
	public boolean validate() {
		KYCNumber annotation = document.getFieldAnnotation();
		int content = (Integer) document.getContent();
		if(content <= annotation.min() || content >= annotation.max()) {
			document.setMessageAndStatus("Content must be at least " + annotation.min() + " and at most " + annotation.max(), false);
			return false;
		}
		document.setMessageAndStatus("Validation Passed!", true);
		return true;
	}

}
