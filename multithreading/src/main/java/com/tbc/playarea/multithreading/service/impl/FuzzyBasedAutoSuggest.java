package com.tbc.playarea.multithreading.service.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.text.similarity.LevenshteinDistance;

import com.tbc.playarea.multithreading.WordCount;
import com.tbc.playarea.multithreading.service.BasicAutoSuggest;

public class FuzzyBasedAutoSuggest extends BasicAutoSuggest {

	public FuzzyBasedAutoSuggest(ConcurrentHashMap<String, WordCount> wordCountMap) {
		super(wordCountMap);
	}
		
	public FuzzyBasedAutoSuggest(String fileName) {
		super(fileName);
	}

	public Collection<String> allPrefixesLevenshtein(String prefix, Trie root) {
		List<String> results = new LinkedList<>();
		if(root.terminal) {
			int levenshteinDist = LevenshteinDistance.getDefaultInstance().apply(prefix, root.value);
			if(levenshteinDist < root.value.length())
				results.add(root.value);
		}
		for(Entry<Character, Trie> entry : root.children.entrySet()) {
			Trie child = entry.getValue();
			Collection<String> childPrefixes = allPrefixesLevenshtein(prefix, child);
			results.addAll(childPrefixes);
		}
		return results;
		
	}

	@Override
	public List<String> autoSuggest(String prefix) {
		return (List<String>) allPrefixesLevenshtein(prefix, getTrie());
	}

}
