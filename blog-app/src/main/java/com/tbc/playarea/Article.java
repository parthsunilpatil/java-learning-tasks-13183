package com.tbc.playarea;

public class Article<T> {
	private String title;
	private String subject;
	private String abstractText;
	private T articleContent;
	
	private Article(ArticleBuilder<T> builder) {
		super();
		this.title = builder.title;
		this.subject = builder.subject;
		this.abstractText = builder.abstractText;
		this.articleContent = builder.articleContent;
	}
	public String getTitle() {
		return title;
	}
	public String getSubject() {
		return subject;
	}
	public String getAbstractText() {
		return abstractText;
	}
	public T getArticleContent() {
		return articleContent;
	}
	
	@Override
	public String toString() {
		return "Article [title=" + title + ", subject=" + subject + ", abstractText=" + abstractText
				+ ", articleContent=" + articleContent + "]";
	}
	
	public static class ArticleBuilder<T> {
		private String title;
		private String subject;
		private String abstractText;
		private T articleContent;
		
		public ArticleBuilder(String title) {
			this.title = title;
			this.subject = null;
			this.abstractText = null;
			this.articleContent = null;
		}

		public ArticleBuilder<T> setSubject(String subject) {
			this.subject = subject;
			return this;
		}

		public ArticleBuilder<T> setAbstractText(String abstractText) {
			this.abstractText = abstractText;
			return this;
		}

		public ArticleBuilder<T> setArticleContent(T articleContent) {
			this.articleContent = articleContent;
			return this;
		}
		
		public Article<T> build() {
			return new Article<>(this);
		}
		
	}
}
