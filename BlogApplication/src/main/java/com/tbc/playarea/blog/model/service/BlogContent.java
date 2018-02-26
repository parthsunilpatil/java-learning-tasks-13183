package com.tbc.playarea.blog.model.service;

import java.util.Map;

public interface BlogContent {
	public String getId();
	public Map<String, BasicContent> getContents();
	public BasicContent getContent(String key);
	public boolean updateContent(String key, BasicContent content);
	public boolean removeContent(String key);
	public boolean addContent(String key, BasicContent content);
}
