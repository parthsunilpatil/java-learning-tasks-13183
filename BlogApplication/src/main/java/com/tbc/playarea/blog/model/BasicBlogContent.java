package com.tbc.playarea.blog.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tbc.playarea.blog.model.service.BasicContent;
import com.tbc.playarea.blog.model.service.BlogContent;

public abstract class BasicBlogContent implements BlogContent {

	protected Map<String, BasicContent> contents;

	public BasicBlogContent() {
		super();
		contents = new ConcurrentHashMap<>();
	}

	@Override
	public Map<String, BasicContent> getContents() {
		return contents;
	}

	@Override
	public BasicContent getContent(String key) {
		return contents.get(key);
	}

	@Override
	public boolean updateContent(String key, BasicContent content) {
		if(!contents.containsKey(key))
			return false;
		else 
			return contents.put(key, content) != null;
	}

	@Override
	public boolean removeContent(String key) {
		return contents.remove(key) != null;
	}

	@Override
	public boolean addContent(String key, BasicContent content) {
		if(contents.containsKey(key))
			return false;
		else
			return contents.put(key, content) == null;
	}

}
