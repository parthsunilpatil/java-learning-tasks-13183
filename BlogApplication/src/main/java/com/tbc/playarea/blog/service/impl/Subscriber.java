package com.tbc.playarea.blog.service.impl;

import com.tbc.playarea.blog.model.ContentTypes;
import com.tbc.playarea.blog.service.BlogSubject;
import com.tbc.playarea.blog.service.Observer;

public class Subscriber implements Observer {
	
	private String id;
	private ContentTypes type;
	private BlogSubject topic;

	public Subscriber(String id, ContentTypes type, BlogSubject topic) {
		super();
		this.id = id;
		this.type = type;
		this.topic = topic;
	}

	@Override
	public ContentTypes getType() {
		return type;
	}

	@Override
	public void consumeMessage(String message) {
		System.out.println(toString());
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Consuming Message: " + message);
		System.out.println("===================================================================");
	}

	@Override
	public void setSubject(BlogSubject subject) {
		this.topic = subject;
	}

	@Override
	public String toString() {
		return "Subscriber [id=" + id + ", type=" + type + ", topic=" + topic + "]";
	}

}
