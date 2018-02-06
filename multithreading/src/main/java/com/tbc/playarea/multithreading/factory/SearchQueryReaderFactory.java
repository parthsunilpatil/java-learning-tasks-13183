package com.tbc.playarea.multithreading.factory;

import com.tbc.playarea.multithreading.service.BasicSearchQueryReader;
import com.tbc.playarea.multithreading.service.impl.CsvFileSearchQueryReader;
import com.tbc.playarea.multithreading.service.impl.TextFileSearchQueryReader;
import com.tbc.playarea.multithreading.service.impl.TsvFileSearchQueryReader;

public class SearchQueryReaderFactory {
	
	public static final String CSV = "csv";
	public static final String TSV = "tsv";
	public static final String TEXT = "txt";
	
	public static BasicSearchQueryReader getSearchQueryReader(String fileName, String type) {
		switch(type) {
		case CSV:
			return new CsvFileSearchQueryReader(fileName);
		case TSV:
			return new TsvFileSearchQueryReader(fileName);
		case TEXT:
			return new TextFileSearchQueryReader(fileName);
		default:
			System.out.println("File of type: " + type + " not supported!");
			System.exit(1);
		}
		return null;
	}

}
