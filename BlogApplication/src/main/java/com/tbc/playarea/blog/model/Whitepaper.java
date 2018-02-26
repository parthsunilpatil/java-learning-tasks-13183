package com.tbc.playarea.blog.model;

import java.util.Map.Entry;

import com.tbc.playarea.blog.model.service.BasicContent;

public class Whitepaper extends BasicBlogContent {
	private String id;
	private String title;
	private String extract;
	
	private Whitepaper(WhitePaperBuilder builder) {
		super();
		this.id = builder.id;
		this.title = builder.title;
		this.extract = builder.extract;
	}

	public String getTitle() {
		return title;
	}

	public String getExtract() {
		return extract;
	}

	@Override
	public String getId() {
		return id;
	}
	
	public static class WhitePaperBuilder {
		private String id;
		private String title;
		private String extract;
		
		public WhitePaperBuilder setExtract(String extract) {
			this.extract = extract;
			return this;
		}

		public WhitePaperBuilder(String id, String title) {
			super();
			this.id = id;
			this.title = title;
		}
		
		public Whitepaper build() {
			return new Whitepaper(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder contentsBuilder = new StringBuilder("WhitePaper [id=").append(id)
				.append(",\n title=").append(title)
				.append(",\n contents=");
		for(Entry<String, BasicContent> content : contents.entrySet()) {
			contentsBuilder.append("\n").append(content.getValue().toString());
		}
		return contentsBuilder.toString();
	}
}
