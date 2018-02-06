package com.tbc.playarea.multithreading.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.tbc.playarea.multithreading.WordCount;
import com.tbc.playarea.multithreading.service.BasicAutoSuggest;

public class PrefixBasedAutoSuggest extends BasicAutoSuggest {

	public PrefixBasedAutoSuggest(ConcurrentHashMap<String, WordCount> wordCountMap) {
		super(wordCountMap);
	}
	
	public PrefixBasedAutoSuggest(String fileName) {
		super(fileName);
	}

	public Collection<String> autoSuggestTrie(String prefix, Trie root) {
		Trie node = root;
		for(char c : prefix.toCharArray()) {
			if(!node.children.containsKey(c)) 
				return Collections.emptyList();
			node = node.children.get(c);
		}
		return node.allPrefixes();
	}

	@Override
	public List<String> autoSuggest(String prefix) {
		return (List<String>) autoSuggestTrie(prefix, getTrie());
	}

}
