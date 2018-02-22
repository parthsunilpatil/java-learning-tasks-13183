package com.tbc.playarea.blog.model;

public class CaseStudy<T> {
	private String title;
	private String abstractText;
	private T content;
	
	private CaseStudy(CaseStudyBuilder<T> builder) {
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

	public static class CaseStudyBuilder<T> {
		private String title;
		private String abstractText;
		private T content;
		
		public CaseStudyBuilder(String title) {
			this.title = title;
			this.abstractText = null;
			this.content = null;
		}

		public CaseStudyBuilder<T> setAbstractText(String abstractText) {
			this.abstractText = abstractText;
			return this;
		}

		public CaseStudyBuilder<T> setContent(T content) {
			this.content = content;
			return this;
		}
		
		public CaseStudy<T> build() {
			return new CaseStudy<T>(this);
		}
		
	}

	@Override
	public String toString() {
		return "CaseStudy [title=" + title + ", abstractText=" + abstractText + ", content=" + content + "]";
	}
}
