package com.tbc.playarea.multithreading.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;

public abstract class BasicSearchQueryReader {
	
	private String fileName;
	private List<String> lines;

	public BasicSearchQueryReader(String fileName) {
		super();
		this.fileName = fileName;
		try {
			this.lines = Files.readAllLines(new File(fileName).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected List<String> splitLinesUrlSafe(String delimiter) {
		List<String> results = new LinkedList<>();
		for(String line : getLines()) {
			for(String word : line.split(delimiter))
				if(!"".equalsIgnoreCase(word))
					results.add(StringEscapeUtils.escapeHtml4(word));
		}
		return results;
	}
	
	protected List<String> splitLinesUrlSafe() {
		List<String> results = new LinkedList<>();
		for(String line : getLines()) {
			if(!"".equalsIgnoreCase(line))
				results.add(StringEscapeUtils.escapeHtml4(line));
		}
		return results;
	}
	
	public abstract List<String> getSearchQueries();

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}

}
