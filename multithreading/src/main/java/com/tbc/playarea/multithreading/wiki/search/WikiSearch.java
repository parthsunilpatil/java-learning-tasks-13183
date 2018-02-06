package com.tbc.playarea.multithreading.wiki.search;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WikiSearch {
	public static void main(String[] args) {
		String[] wikiKeys = {"Java", "Thread", "Bash"};
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (String key : wikiKeys) {
			executor.execute(new WikiHttpClient(key, "/home/parthp/wiki_searches/"));
		}
		executor.shutdown();
	}
}
