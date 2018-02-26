package com.tbc.playarea.blog.model.service;

import com.tbc.playarea.blog.model.ContentTypes;

public interface BasicContent {
	public ContentTypes getType();
	public BasicContent getContentInstance();
}
