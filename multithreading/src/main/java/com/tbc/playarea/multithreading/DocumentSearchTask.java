package com.tbc.playarea.multithreading;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveAction;

public class DocumentSearchTask extends RecursiveAction {
	
	private Document document;
	private ConcurrentHashMap<String, WordCount> wordCountMap;

	public DocumentSearchTask(Document document, ConcurrentHashMap<String, WordCount> wordCountMap) {
		super();
		this.document = document;
		this.wordCountMap = wordCountMap;
	}
	
	public ConcurrentHashMap<String, WordCount> getWordCountMap() {
		return wordCountMap;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	public void setWordCountMap(ConcurrentHashMap<String, WordCount> wordCountMap) {
		this.wordCountMap = wordCountMap;
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