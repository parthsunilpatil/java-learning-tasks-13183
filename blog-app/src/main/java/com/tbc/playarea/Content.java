package com.tbc.playarea;

public class Content<T> {
	private String title;
	private T content;
	public Content(String title, T content) {
		super();
		this.title = title;
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public T getContent() {
		return content;
	}
	@Override
	public String toString() {
		return "Content [title=" + title + ", content=" + content + "]";
	}
}
