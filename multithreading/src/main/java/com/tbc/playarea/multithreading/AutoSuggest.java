package com.tbc.playarea.multithreading;

import java.util.concurrent.ConcurrentHashMap;

import com.tbc.playarea.multithreading.service.impl.Trie;

public class AutoSuggest {

	private static final int LOWER_BOUND = (int)'A';
	private static final int UPPER_BOUND = (int)'z';
	private Trie trie;
	private ConcurrentHashMap<String, WordCount> wordCountMap;
	public AutoSuggest(ConcurrentHashMap<String, WordCount> wordCountMap) {
		super();
		this.trie = new Trie();
		this.wordCountMap = wordCountMap;
		for(String key : wordCountMap.keySet()) {
			if(checkAsciiRange(key))
				trie.insert(key);
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

	public ConcurrentHashMap<String, WordCount> getWordCountMap() {
		return wordCountMap;
	}
	
	public static void main(String[] args) {
		try {
			ConcurrentHashMap<String, WordCount> wordCountMap = new ConcurrentHashMap<>();
			String folderName = "/home/parthp/wiki_searches/";
			WordCountForkJoin.countWords(wordCountMap, folderName);
			AutoSuggest driver = new AutoSuggest(wordCountMap);
			Thread.sleep(500);
			Trie trie = driver.getTrie();
			trie.autoSuggest("Ja").stream().forEach(t -> {
				System.out.println(wordCountMap.get(t));
			});
			
			System.out.println("Second Run:");
			
			trie.autoSuggest("Ja").stream().forEach(t -> {
				System.out.println(wordCountMap.get(t));
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
