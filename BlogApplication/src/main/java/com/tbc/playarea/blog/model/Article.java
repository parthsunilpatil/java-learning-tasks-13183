package com.tbc.playarea.blog.model;

import java.util.Map.Entry;

import com.tbc.playarea.blog.model.service.BasicContent;

public class Article extends BasicBlogContent {
	
	private String id;
	private String title;
	private String extract;
	private String authorInfo;
	
	private Article(ArticleBuilder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.extract = builder.extract;
		this.authorInfo = builder.authorInfo;
	}
	
	public String getExtract() {
		return extract;
	}

	public String getAuthorInfo() {
		return authorInfo;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		StringBuilder contentsBuilder = new StringBuilder("Article [id=").append(id)
				.append(",\n title=").append(title)
				.append(",\n contents=");
		for(Entry<String, BasicContent> content : contents.entrySet()) {
			contentsBuilder.append("\n").append(content.getValue().toString());
		}
		return contentsBuilder.toString();
	}

	public static class ArticleBuilder {
		private String id;
		private String title;
		private String extract;
		private String authorInfo;

		public ArticleBuilder(String id, String title) {
			super();
			this.id = id;
			this.title = title;
		}
		
		public ArticleBuilder setExtract(String extract) {
			this.extract = extract;
			return this;
		}
		public ArticleBuilder setAuthorInfo(String authorInfo) {
			this.authorInfo = authorInfo;
			return this;
		}
		
		public Article build() {
			return new Article(this);
		}
	}

}
