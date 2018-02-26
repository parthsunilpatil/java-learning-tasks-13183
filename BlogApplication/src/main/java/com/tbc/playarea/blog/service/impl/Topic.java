package com.tbc.playarea.blog.service.impl;

import java.util.List;
import java.util.Map;

import com.tbc.playarea.blog.model.ContentTypes;
import com.tbc.playarea.blog.model.service.BasicContent;
import com.tbc.playarea.blog.model.service.BlogContent;
import com.tbc.playarea.blog.service.Observer;

public class Topic<T extends BlogContent> extends BasicTopic {
	
	private T blogContent;
	private BlogTopic blogInstance;
	
	protected void notifyBlog(String message) {
		blogInstance = BlogTopic.getInstance();
		blogInstance.changed = true;
		blogInstance.notifyAllObserevers(message);
	}

	public Topic(T blogContent) {
		super();
		this.blogContent = blogContent;
		notifyBlog("New Blog Content created:" + blogContent.toString());
	}
	
	@Override
	public void register(Observer observer) {
		super.register(observer);
		blogInstance.register(observer);
	}

	@Override
	public void addContent(String contentKey, BasicContent content) {
		if(blogContent.addContent(contentKey, content)) {
			changed = true;
			notifyObserevers("Content: " + content.toString() + " added with key: " + contentKey, content.getType());
		} else {
			throw new IllegalArgumentException("Content already present!");
		}
	}

	@Override
	public void updateContent(String contentkey, BasicContent content) {
		if(blogContent.updateContent(contentkey, content)) {
			changed = true;
			notifyObserevers("Content id:" + contentkey + " updated with new content: " + content.toString(), content.getType());
		} else {
			throw new IllegalArgumentException("Content not present!");
		}
	}

	@Override
	public void removeContent(String contentKey) {
		ContentTypes type = getContent(contentKey).getType();
		if(blogContent.removeContent(contentKey)) {
			changed = true;
			notifyObserevers("Content id: " + contentKey + " removed!", type);
		}
	}

	@Override
	public BasicContent getContent(String contentKey) {
		return blogContent.getContent(contentKey);
	}

	@Override
	public void notifyObserevers(String message, ContentTypes type) {
		Map<ContentTypes, List<Observer>> observersLocal = observersToNotify();
		if(observersLocal == null)
			return;
		for(Observer observer : observersLocal.get(type)) {
			observer.consumeMessage(message);
		}
	}

	@Override
	public String toString() {
		return "Topic [blogContent=" + blogContent + "]";
	}

}
