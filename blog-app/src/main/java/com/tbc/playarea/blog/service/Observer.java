package com.tbc.playarea.blog.service;

public interface Observer<T> {
	public void setSubject(BlogSubject<T> subject);
	public void consumeMessage(String message);
}
