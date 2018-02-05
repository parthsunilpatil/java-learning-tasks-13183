package com.tbc.playarea.multithreading;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Document {
	private final List<String> lines;
	private final String fileName;
	
	public Document(List<String> lines, String fileName) {
		super();
		this.lines = lines;
		this.fileName = fileName;
	}

	public List<String> getLines() {
		return lines;
	}

	public String getFileName() {
		return fileName;
	}
	
	public static Document fromFile(File file) {
		try {
			List<String> lines = Files.readAllLines(file.toPath());
			return new Document(lines, file.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
