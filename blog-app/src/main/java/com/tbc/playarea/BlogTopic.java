package com.tbc.playarea;

import java.util.Map;

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
