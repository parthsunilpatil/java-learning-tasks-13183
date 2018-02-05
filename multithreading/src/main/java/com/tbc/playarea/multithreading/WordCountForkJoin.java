package com.tbc.playarea.multithreading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class WordCountForkJoin {
	
	public static void main(String[] args) throws InterruptedException {
		ConcurrentHashMap<String, WordCount> wordCountMap = new ConcurrentHashMap<>();
		//TrieMap<String, WordCount> wordCountMap = new TrieMap<>();
		String folderName = "/home/parthp/wiki_searches/";
		countWords(wordCountMap, folderName);
		try (
			PrintWriter pw = new PrintWriter(new File("/home/parthp/wiki_words_count/"));
		){
			for(Entry<String, WordCount> word : wordCountMap.entrySet()) {
				pw.println(word.getKey() + " :: " + word.getValue().toString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(wordCountMap.get("Jawa"));
	}

	public static void countWords(ConcurrentHashMap<String, WordCount> wordCountMap, String folderName)
			throws InterruptedException {
		Folder folder = Folder.fromDirectory(new File(folderName));
		ForkJoinPool pool = new ForkJoinPool();
		System.out.println("Starting Threads at: " + System.currentTimeMillis());
		pool.invoke(new FolderSearchTask(folder, wordCountMap));
		pool.awaitTermination(500, TimeUnit.MILLISECONDS);
	}

}
