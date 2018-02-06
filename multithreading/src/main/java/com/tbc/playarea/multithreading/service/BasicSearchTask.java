package com.tbc.playarea.multithreading.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveAction;

import com.tbc.playarea.multithreading.WordCount;

public abstract class BasicSearchTask extends RecursiveAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6531564937480760460L;
	protected ConcurrentHashMap<String, WordCount> wordCountMap;

	public BasicSearchTask(ConcurrentHashMap<String, WordCount> wordCountMap) {
		super();
		this.wordCountMap = wordCountMap;
	}

	public ConcurrentHashMap<String, WordCount> getWordCountMap() {
		return wordCountMap;
	}

	public void setWordCountMap(ConcurrentHashMap<String, WordCount> wordCountMap) {
		this.wordCountMap = wordCountMap;
	}
	
	protected abstract void compute();	
	
}
