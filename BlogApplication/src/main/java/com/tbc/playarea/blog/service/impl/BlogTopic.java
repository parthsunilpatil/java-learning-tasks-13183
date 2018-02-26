package com.tbc.playarea.blog.service.impl;

import com.tbc.playarea.blog.model.ContentTypes;
import com.tbc.playarea.blog.model.service.BasicContent;

public class BlogTopic extends BasicTopic {
	
	private static final BlogTopic INSTANCE = new BlogTopic();

	private BlogTopic() {
		super();
	}
	
	public static BlogTopic getInstance() {
		return INSTANCE;
	}

	@Override
	public void notifyObserevers(String message, ContentTypes type) {
		throw new IllegalAccessError();
	}

	@Override
	public void addContent(String contentKey, BasicContent content) {
		throw new IllegalAccessError();
	}

	@Override
	public void updateContent(String contentkey, BasicContent content) {
		throw new IllegalAccessError();
	}

	@Override
	public void removeContent(String contentKey) {
		throw new IllegalAccessError();
	}

	@Override
	public BasicContent getContent(String contentKey) {
		throw new IllegalAccessError();
	}

}
