package com.tbc.playarea.multithreading.service.impl;

import java.util.List;

import com.tbc.playarea.multithreading.service.BasicSearchQueryReader;

public class CsvFileSearchQueryReader extends BasicSearchQueryReader {

	public CsvFileSearchQueryReader(String fileName) {
		super(fileName);
	}

	@Override
	public List<String> getSearchQueries() {
		return splitLinesUrlSafe(",");
	}

}
