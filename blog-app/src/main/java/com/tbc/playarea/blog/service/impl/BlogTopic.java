package com.tbc.playarea.blog.service.impl;

import java.util.Map;

import com.tbc.playarea.blog.service.Observer;

public class BlogTopic<T> extends BasicTopic<T> {
	
	private static final BlogTopic<?> INSTANCE = new BlogTopic<>();

	private BlogTopic() {
		super();
	}
	
	public static BlogTopic<?> getInstance() {
		return INSTANCE;
	}

	@Override
	public void addOrModifyContent(String key, T content) {
		throw new IllegalAccessError();
	}

	@Override
	public void removeContent(String key) {
		throw new IllegalAccessError();
	}

	@Override
	public Map<String, T> getContent(Observer<T> observer) {
		throw new IllegalAccessError();
	}

}
