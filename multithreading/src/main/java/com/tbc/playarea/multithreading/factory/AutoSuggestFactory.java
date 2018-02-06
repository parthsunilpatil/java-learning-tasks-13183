package com.tbc.playarea.multithreading.factory;

import com.tbc.playarea.multithreading.service.BasicAutoSuggest;
import com.tbc.playarea.multithreading.service.impl.FuzzyBasedAutoSuggest;
import com.tbc.playarea.multithreading.service.impl.PrefixBasedAutoSuggest;

public class AutoSuggestFactory {
	public static final String PREFIX = "prefix";
	public static final String FUZZY = "fuzzy";
	
	public static BasicAutoSuggest getAutoSuggestDriver(String fileName, String type) {
		switch(type) {
		case PREFIX:
			return new PrefixBasedAutoSuggest(fileName);
		case FUZZY:
			return new FuzzyBasedAutoSuggest(fileName);
		default:
			System.out.println("Auto Suggest of type: " + type + " is not supported.");
			System.exit(1);
		}
		return null;
	}
}
