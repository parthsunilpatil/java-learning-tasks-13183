package com.tbc.playarea.multithreading.service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.tbc.playarea.multithreading.WordCount;
import com.tbc.playarea.multithreading.service.impl.Trie;

public abstract class BasicAutoSuggest {
	private static final int LOWER_BOUND = (int)'A';
	private static final int UPPER_BOUND = (int)'z';
	private Trie trie;
	
	public BasicAutoSuggest(ConcurrentHashMap<String, WordCount> wordCountMap) {
		super();
		this.trie = new Trie();
		for(String key : wordCountMap.keySet()) {
			if(checkAsciiRange(key))
				trie.insert(key);
		}
	}
	
	public BasicAutoSuggest(String fileName) {
		super();
		this.trie = new Trie();
		try {
			Files.lines(new File(fileName).toPath()).forEach(x -> trie.insert(x.split(" :: ")[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkAsciiRange(String word) {
		for(int i = 0; i < word.length(); i++) 
			if(word.charAt(i) < LOWER_BOUND || word.charAt(i) > UPPER_BOUND) 
				return false;
		return true;
	}
	
	public Trie getTrie() {
		return trie;
	}
	
	public abstract List<String> autoSuggest(String prefix);
}
