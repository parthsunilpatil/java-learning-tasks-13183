package com.tbc.playarea.multithreading;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	
	public Collection<String> autoSuggest(String prefix) {
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
}