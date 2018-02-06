package com.tbc.playarea.multithreading.service.impl;

import java.util.List;

import com.tbc.playarea.multithreading.service.BasicSearchQueryReader;

public class TsvFileSearchQueryReader extends BasicSearchQueryReader {

	public TsvFileSearchQueryReader(String fileName) {
		super(fileName);
	}

	@Override
	public List<String> getSearchQueries() {
		return splitLinesUrlSafe("\t");
	}

}
