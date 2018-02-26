package com.tbc.playarea.blog.service;

import com.tbc.playarea.blog.model.ContentTypes;
import com.tbc.playarea.blog.model.service.BasicContent;

public interface BlogSubject {
	public void register(Observer observer);
	
	public void notifyAllObserevers(String message);
	public void notifyObserevers(String message, ContentTypes type);
	
	public void addContent(String contentKey, BasicContent content);
	public void updateContent(String contentkey, BasicContent content);
	public void removeContent(String contentKey);
	public BasicContent getContent(String contentKey);
}
