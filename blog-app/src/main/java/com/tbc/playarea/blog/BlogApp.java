package com.tbc.playarea.blog;

import com.tbc.playarea.blog.model.Article;
import com.tbc.playarea.blog.model.Article.ArticleBuilder;
import com.tbc.playarea.blog.service.BlogSubject;
import com.tbc.playarea.blog.service.Observer;
import com.tbc.playarea.blog.service.impl.Subscriber;
import com.tbc.playarea.blog.service.impl.Topic;

public class BlogApp {
	public static void main(String[] args) {
		BlogSubject<Article<String>> strArticle1 = new Topic<>("Creating new String Article");
		Observer<Article<String>> strArticleObs1 = new Subscriber<>("observer.strArticle1.1");
		Observer<Article<String>> strArticleObs2 = new Subscriber<>("observer.strArticle1.2");
		strArticle1.register(strArticleObs1);
		strArticle1.register(strArticleObs2);
		strArticleObs1.setSubject(strArticle1);
		strArticleObs2.setSubject(strArticle1);
		
		strArticle1.addOrModifyContent("AS1", new ArticleBuilder<String>("AS1")
				.setSubject("String Article 1")
				.setAbstractText("about a string article")
				.setArticleContent("this is a string article")
				.build());
		
		BlogSubject<Article<Integer>> intArticle1 = new Topic<>("Creating new Integer Article");
		Observer<Article<Integer>> intArticleObs1 = new Subscriber<>("observer.intArticle1.1");
		Observer<Article<Integer>> intArticleObs2 = new Subscriber<>("observer.intArticle1.2");
		intArticle1.register(intArticleObs1);
		intArticle1.register(intArticleObs2);
		intArticleObs1.setSubject(intArticle1);
		intArticleObs2.setSubject(intArticle1);
		
		intArticle1.addOrModifyContent("AI1", new ArticleBuilder<Integer>("AI1")
				.setSubject("Integer number 1")
				.setArticleContent(1)
				.build());
		
	}
}