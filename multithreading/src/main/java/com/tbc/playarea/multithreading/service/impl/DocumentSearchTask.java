package com.tbc.playarea.multithreading.service.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.tbc.playarea.multithreading.WordCount;
import com.tbc.playarea.multithreading.dao.Document;
import com.tbc.playarea.multithreading.service.BasicSearchTask;

public class DocumentSearchTask extends BasicSearchTask {
	
	private Document document;

	public DocumentSearchTask(Document document, ConcurrentHashMap<String, WordCount> wordCountMap) {
		super(wordCountMap);
		this.document = document;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	private String[] wordsIn(String line) {
		return line.trim().split("(\\s|\\p{Punct})+");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4987666845919152753L;

	@Override
	protected void compute() {
		countWords();
	}

	private void countWords() {
		System.out.println("Thread: " + Thread.currentThread().getName() + " started at: " + System.currentTimeMillis());
		for(String line : document.getLines()) {
			for(String word : wordsIn(line)) {
				WordCount wordCount = null;
				if(wordCountMap.containsKey(word)) {
					 wordCount = wordCountMap.get(word);
				} else {
					wordCount = new WordCount(word);
				}
				List<String> documentNames = wordCount.getDocuments();
				if(documentNames.isEmpty() || !documentNames.contains(document.getFileName()))
					documentNames.add(document.getFileName());
				wordCount.setCount(wordCount.getCount() + 1);
				wordCount.setDocuments(documentNames);
				wordCountMap.put(word, wordCount);
			}
		}
		System.out.println("Thread: " + Thread.currentThread().getName() + " completed at: " + System.currentTimeMillis());
	}

}
