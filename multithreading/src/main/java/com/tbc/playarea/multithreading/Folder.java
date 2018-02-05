package com.tbc.playarea.multithreading;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Folder {
	private final List<Document> documents;

	public List<Document> getDocuments() {
		return documents;
	}

	public Folder(List<Document> documents) {
		super();
		this.documents = documents;
	}
	
	public static Folder fromDirectory(File dir) {
		List<Document> documents = new LinkedList<>();
		for(File entry : dir.listFiles()) {
			if(!entry.isDirectory()) {
				documents.add(Document.fromFile(entry));
			}
		}
		return new Folder(documents);
	}
}
