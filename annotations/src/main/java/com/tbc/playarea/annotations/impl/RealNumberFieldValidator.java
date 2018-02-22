package com.tbc.playarea.annotations.impl;

import com.tbc.playarea.annotations.BasicFieldValidation;
import com.tbc.playarea.annotations.KYCRealNumber;

public class RealNumberFieldValidator implements BasicFieldValidation {
	private ValidationDocument<KYCRealNumber> document;

	public ValidationDocument<KYCRealNumber> getDocument() {
		return document;
	}

	public RealNumberFieldValidator(ValidationDocument<KYCRealNumber> document) {
		super();
		this.document = document;
	}

	@Override
	public boolean validate() {
		KYCRealNumber annotation = document.getFieldAnnotation();
		int content = (Integer) document.getContent();
		if(content <= annotation.min() || content >= annotation.max()) {
			document.setMessageAndStatus("Content must be at least " + annotation.min() + " and at most " + annotation.max(), false);
			return false;
		}
		document.setMessageAndStatus("Validation Passed!", true);
		return true;
	}

}
