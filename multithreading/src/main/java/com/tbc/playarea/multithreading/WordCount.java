package com.tbc.playarea.multithreading;

import java.util.LinkedList;
import java.util.List;

public class WordCount {
	
	private final String word;
	private List<String> documents;
	private long count;
	
	public WordCount(String word, List<String> documents, long count) {
		super();
		this.word = word;
		this.documents = documents;
		this.count = count;
	}

	public WordCount(String word) {
		super();
		this.word = word;
		this.documents = new LinkedList<>();
		this.count = 0;
	}

	@Override
	public String toString() {
		return "WordCount [word=" + word + ", documents=" + String.join(",", documents) + ", count=" + count + "]";
	}

	public List<String> getDocuments() {
		return documents;
	}

	public void setDocuments(List<String> documents) {
		this.documents = documents;
	}

	public String getWord() {
		return word;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
}
