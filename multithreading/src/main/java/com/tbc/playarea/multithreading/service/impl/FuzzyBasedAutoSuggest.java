package com.tbc.playarea.multithreading.service.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.tbc.playarea.multithreading.WordCount;
import com.tbc.playarea.multithreading.service.BasicAutoSuggest;

public class FuzzyBasedAutoSuggest extends BasicAutoSuggest {

	public FuzzyBasedAutoSuggest(ConcurrentHashMap<String, WordCount> wordCountMap) {
		super(wordCountMap);
	}
		
	public FuzzyBasedAutoSuggest(String fileName) {
		super(fileName);
	}
	
	public int levenshteinDistance(String source, String target) {
		String s = source.toLowerCase(), t = target.toLowerCase();
		int[] costs = new int [t.length() + 1];
		
		for(int i = 0; i < costs.length; i++)
			costs[i] = i;
		
		for(int i = 1; i <= s.length(); i++) {
			costs[0] = i;
			int nw = i - 1;
			for(int j = 1; j <= t.length(); j++) {
				int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), t.charAt(i - 1) == t.charAt(j - 1) ? nw : nw + 1);
				nw = costs[j];
				costs[j] = cj;
			}
		}
		
		return costs[t.length()];
	}

	public Collection<String> allPrefixesLevenshtein(String prefix, Trie root) {
		List<String> results = new LinkedList<>();
		if(root.terminal) {
			int levenshteinDist = levenshteinDistance(prefix, root.value);
			if(levenshteinDist >= 0 && levenshteinDist <= root.value.length())
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
