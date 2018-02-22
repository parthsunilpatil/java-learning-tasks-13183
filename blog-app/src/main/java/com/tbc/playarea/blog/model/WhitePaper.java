package com.tbc.playarea.blog.model;

public class WhitePaper<T> {
	private String title;
	private String abstractText;
	private T content;
	
	private WhitePaper(WhitePaperBuilder<T> builder) {
		super();
		this.title = builder.title;
		this.abstractText = builder.abstractText;
		this.content = builder.content;
	}
	
	public String getTitle() {
		return title;
	}

	public String getAbstractText() {
		return abstractText;
	}

	public T getContent() {
		return content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public static class WhitePaperBuilder<T> {
		private String title;
		private String abstractText;
		private T content;
		
		public WhitePaperBuilder(String title) {
			this.title = title;
			this.abstractText = null;
			this.content = null;
		}

		public WhitePaperBuilder<T> setAbstractText(String abstractText) {
			this.abstractText = abstractText;
			return this;
		}

		public WhitePaperBuilder<T> setContent(T content) {
			this.content = content;
			return this;
		}
		
		public WhitePaper<T> build() {
			return new WhitePaper<T>(this);
		}	
		
	}

	@Override
	public String toString() {
		return "WhitePaper [title=" + title + ", abstractText=" + abstractText + ", content=" + content + "]";
	}
}
