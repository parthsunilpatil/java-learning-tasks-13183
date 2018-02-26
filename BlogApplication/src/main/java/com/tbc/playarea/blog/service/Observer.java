package com.tbc.playarea.blog.service;

import com.tbc.playarea.blog.model.ContentTypes;

public interface Observer{
	public ContentTypes getType();
	public void setSubject(BlogSubject subject);
	public void consumeMessage(String message);
}
