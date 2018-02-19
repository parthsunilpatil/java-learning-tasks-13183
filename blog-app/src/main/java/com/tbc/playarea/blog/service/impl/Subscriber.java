package com.tbc.playarea.blog.service.impl;

import com.tbc.playarea.blog.service.BlogSubject;
import com.tbc.playarea.blog.service.Observer;

public class Subscriber<T> implements Observer<T> {
	
	private String id;
	private BlogSubject<T> topic;

	public Subscriber(String id) {
		super();
		this.id = id;
	}

	@Override
	public void setSubject(BlogSubject<T> subject) {
		this.topic = subject;
	}

	@Override
	public void consumeMessage(String message) {
		System.out.println(toString());
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Consuming Message: " + message);
		System.out.println("===================================================================");
	}

	@Override
	public String toString() {
		return "Subscriber [id=" + id + ", topic=" + topic + "]";
	}

}
