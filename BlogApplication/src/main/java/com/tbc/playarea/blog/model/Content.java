package com.tbc.playarea.blog.model;

import com.tbc.playarea.blog.model.service.BasicContent;

public class Content<T> implements BasicContent {
	
	private String id;
	private String description;
	private ContentTypes type;
	private T content;
	
	public T getContent() {
		return content;
	}

	public Content<T> setDescription(String description) {
		this.description = description;
		return this;
	}

	public Content<T> setContent(T content) {
		this.content = content;
		return this;
	}

	public Content(String id, String description, ContentTypes type, T content) {
		super();
		this.id = id;
		this.description = description;
		this.type = type;
		this.content = content;
	}

	@Override
	public BasicContent getContentInstance() {
		return this;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "ArticleContent [id=" + id + ",\n description=" + description + ",\n content=" + content + "]";
	}

	@Override
	public ContentTypes getType() {
		return type;
	}

}
