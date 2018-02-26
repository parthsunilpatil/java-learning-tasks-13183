package com.tbc.playarea.blog;

import com.tbc.playarea.blog.model.Article;
import com.tbc.playarea.blog.model.Content;
import com.tbc.playarea.blog.model.ContentTypes;
import com.tbc.playarea.blog.model.Whitepaper;
import com.tbc.playarea.blog.service.BlogSubject;
import com.tbc.playarea.blog.service.Observer;
import com.tbc.playarea.blog.service.impl.Subscriber;
import com.tbc.playarea.blog.service.impl.Topic;

public class Blogger {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Article article = new Article.ArticleBuilder("article.1", "Article 1")
				.setAuthorInfo("abc")
				.setExtract("this contains articles")
				.build();
		BlogSubject articleSubject = new Topic<Article>(article);
		Observer articleStrObserver = new Subscriber("str.observer.1", ContentTypes.STRING, articleSubject);
		articleSubject.register(articleStrObserver);
		Observer articleIntObserver = new Subscriber("int.observer.1", ContentTypes.INTEGER, articleSubject);
		articleSubject.register(articleIntObserver);
		articleSubject.addContent("string.content", new Content<String>("string.content"
				, "String Content"
				, ContentTypes.STRING
				, "String contents of the article"));
		articleSubject.addContent("int.content", new Content<Integer>("int.content"
				, "Integer Content"
				, ContentTypes.INTEGER
				, 1));
		articleSubject.addContent("string.content.mod", new Content<String>("string.content.mod"
				, "String Content"
				, ContentTypes.STRING
				, "String content to be modified"));
		articleSubject.addContent("int.content.mod", new Content<Integer>("int.content.mod"
				, "Integer Content (Modification)"
				, ContentTypes.INTEGER
				, 1));
		articleSubject.addContent("string.content.del", new Content<String>("string.content.del"
				, "String Content"
				, ContentTypes.STRING
				, "String content to be removed"));
		articleSubject.addContent("int.content.del", new Content<Integer>("int.content.del"
				, "Integer  Content (Deletion)"
				, ContentTypes.INTEGER
				, 1));
		
		articleSubject.updateContent("string.content.mod", ((Content<String>) article.getContent("string.content.mod").getContentInstance())
				.setDescription("String Content (Modified)")
				.setContent("String content modified"));
		
		articleSubject.updateContent("int.content.mod", ((Content<Integer>) article.getContent("int.content.mod").getContentInstance())
				.setDescription("Integer Content (Modified)")
				.setContent(2));
		
		articleSubject.removeContent("string.content.del");
		articleSubject.removeContent("int.content.del");
		
		Whitepaper whitePaper = new Whitepaper.WhitePaperBuilder("whitepaper.1", "Whitepaper 1")
				.setExtract("this contains whitepapers")
				.build();
		BlogSubject whitepaperSubject = new Topic<Whitepaper>(whitePaper);
		Observer whitePaperStrObserver = new Subscriber("str.observer.wp.1", ContentTypes.STRING, whitepaperSubject);
		Observer whitePaperIntObserver = new Subscriber("int.observer.wp.1", ContentTypes.INTEGER, whitepaperSubject);
		whitepaperSubject.register(whitePaperStrObserver);
		whitepaperSubject.register(whitePaperIntObserver);
		whitepaperSubject.addContent("whitepaper.content.string", new Content<String>("whitepaper.content.string",
				"White paper String",
				ContentTypes.STRING,
				"white paper string content"));
		whitepaperSubject.addContent("whitepaper.content.int", new Content<Integer>("whitepaper.content.int",
				"White paper Integer",
				ContentTypes.INTEGER,
				1));
		
		whitePaper.updateContent("whitepaper.content.string", ((Content<String>) whitePaper.getContent("whitepaper.content.string").getContentInstance())
				.setDescription("White paper String (Modified)")
				.setContent("white paper string modified"));
		whitePaper.removeContent("whitepaper.content.int");
	}
}
