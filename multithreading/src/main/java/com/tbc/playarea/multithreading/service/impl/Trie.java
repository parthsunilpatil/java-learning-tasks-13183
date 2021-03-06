package com.tbc.playarea.multithreading.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.text.similarity.LevenshteinDistance;

import com.tbc.playarea.multithreading.dao.Cacheable;

public class Trie {
	protected final Map<Character, Trie> children;
	protected String value;
	protected boolean terminal = false;
	
	public Trie() {
		this(null);
	}
	
	private Trie(String value) {
		this.value = value;
		this.children = new HashMap<>();
	}
	
	protected void add(char c) {
		String val;
		if(this.value == null) {
			val = Character.toString(c);
		} else {
			val = this.value + c;
		}
		children.put(c, new Trie(val));
	}
	
	public void insert(String word) {
		Trie node = this;
		for(char c : word.toCharArray()) {
			if(!node.children.containsKey(c)) 
				node.add(c);
			node = node.children.get(c);
		}
		node.terminal = true;
	}
	
	public String find(String word) {
		Trie node = this;
		for(char c : word.toCharArray()) {
			if(!node.children.containsKey(c))
				return "";
			node = node.children.get(c);
		}
		return node.value;
	}
	
	@Cacheable
	public Collection<String> autoSuggest(String prefix) {
		System.out.println("Executing Auto Suggest!");
		Trie node = this;
		for(char c : prefix.toCharArray()) {
			if(!node.children.containsKey(c)) 
				return Collections.emptyList();
			node = node.children.get(c);
		}
		return node.allPrefixes();
	}

	protected Collection<String> allPrefixes() {
		List<String> results = new LinkedList<>();
		if(this.terminal)
			results.add(this.value);
		for(Entry<Character, Trie> entry : children.entrySet()) {
			Trie child = entry.getValue();
			Collection<String> childPrefixes = child.allPrefixes();
			results.addAll(childPrefixes);
		}
		return results;
	}
	
	public Collection<String> allPrefixesLevenshtein(String prefix) {
		List<String> results = new LinkedList<>();
		if(this.terminal) {
			int levenshteinDist = LevenshteinDistance.getDefaultInstance().apply(prefix, this.value);
			if(levenshteinDist >= 0 && levenshteinDist <= this.value.length())
				results.add(this.value);
		}
		for(Entry<Character, Trie> entry : children.entrySet()) {
			Trie child = entry.getValue();
			Collection<String> childPrefixes = child.allPrefixesLevenshtein(prefix);
			results.addAll(childPrefixes);
		}
		return results;
		
	}
}