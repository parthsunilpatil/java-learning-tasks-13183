package com.tbc.playarea;

import java.util.HashMap;
import java.util.Map;

public class Topic<T> extends BasicTopic<T> {
	
	private Map<String, T> content;
	private BlogTopic<T> blogInstance;
	
	public Topic(Map<String, T> content, String message) {
		this.content = new HashMap<>();
		this.content.putAll(content);
		notifyBlog(message);
	}

	@SuppressWarnings("unchecked")
	protected void notifyBlog(String message) {
		blogInstance = (BlogTopic<T>) BlogTopic.getInstance();
		blogInstance.changed = true;
		blogInstance.notifyObservers(message);
	}
	
	public Topic(String message) {
		this.content = new HashMap<>();
		notifyBlog(message);
	}
	
	@Override
	public void register(Observer<T> observer) {
		super.register(observer);
		blogInstance.register(observer);
	}
	
	@Override
	public void unregister(Observer<T> observer) {
		super.unregister(observer);
		blogInstance.unregister(observer);
	}

	@Override
	public Map<String, T> getContent(Observer<T> observer) {
		return content;
	}
	
	@Override
	public void addOrModifyContent(String key, T content) {
		this.content.put(key, content);
		this.changed = true;
		notifyObservers("Content: " + content.toString() + " added/modified.");
	}

	@Override
	public void removeContent(String key) {
		T removed = this.content.remove(key);
		this.changed = true;
		notifyObservers("Content: " + removed + " removed");
	}

	@Override
	public String toString() {
		return "ArticleTopic [content=" + content + "]";
	}

}
