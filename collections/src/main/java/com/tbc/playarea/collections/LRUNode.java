package com.tbc.playarea.collections;

public class LRUNode {
	private String key;
	private String content;
	protected LRUNode prev;
	protected LRUNode next;
	public LRUNode(String key, String content) {
		super();
		this.setKey(key);
		this.content = content;
		this.prev = null;
		this.next = null;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
