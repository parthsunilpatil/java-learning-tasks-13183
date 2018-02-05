package com.tbc.playarea.collections;

import java.util.HashMap;

public class LRUCache {
	private HashMap<String, LRUNode> cache;
	private int capacity;
	private LRUNode head;
	private LRUNode tail;
	
	public LRUCache(int capacity) {
		super();
		this.capacity = capacity;
		this.cache = new HashMap<>();
		this.head = this.tail = null;
	}
	
	public String get(String key) {
		if(cache.containsKey(key)) {
			LRUNode node = cache.get(key);
			remove(node);
			setHead(node);
			return node.getContent();
		}
		return "";
	}

	private void setHead(LRUNode node) {
		node.next = head;
		node.prev = null;
		if(head != null) 
			head.prev = node;
		head = node;
		if(tail == null)
			tail = head;
	}

	private void remove(LRUNode node) {
		if(node.prev != null) 
			node.prev.next = node.next;
		else
			head = node.next;
		
		if(node.next != null)
			node.next.prev = node.prev;
		else
			tail = node.prev;
	}
	
	public void put(String key, String value) {
		if(cache.containsKey(key)) {
			LRUNode old = cache.get(key);
			old.setContent(value);
			remove(old);
			setHead(old);
		} else {
			LRUNode node = new LRUNode(key, value);
			if(cache.size() >= capacity) {
				cache.remove(tail.getKey());
				remove(tail);
				setHead(node);
			} else {
				setHead(node);
			}
			
			cache.put(key, node);
		}
	}
	
	public static void main(String[] args) {
		LRUCache lru = new LRUCache(10);
		lru.put("a", "A");
		lru.put("b", "B");
		lru.put("c", "C");
		lru.put("d", "D");
		lru.put("e", "E");
		lru.put("b", "BB");
		lru.put("c", "CC");
		lru.put("d", "DD");
		lru.put("e", "EE");
		lru.put("d", "DDD");
		lru.put("e", "EEE");
		lru.put("a", "AA");
		lru.put("b", "BBB");
		lru.put("c", "CCC");
		lru.put("d", "DDD");
		lru.put("a", "AAA");
		lru.put("b", "BBBB");
		lru.put("c", "CCCC");
		lru.put("d", "DDDD");
		lru.put("b", "BBBBB");
		lru.put("c", "CCCCC");
		lru.put("d", "DDDDD");
		
		System.out.println(":::::");
		System.out.println(lru.get("a"));
		System.out.println(lru.get("b"));
		System.out.println(lru.get("c"));
		System.out.println(lru.get("d"));
		System.out.println(lru.get("e"));
	}
	
}
