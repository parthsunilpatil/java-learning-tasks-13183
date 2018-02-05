package com.tbc.playarea.multithreading;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import com.romix.scala.collection.concurrent.TrieMap;

public class FolderSearchTaskTrieMap extends RecursiveAction {
	
	private Folder folder;
	private TrieMap<String, WordCount> wordCountMap;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2766930612021124588L;

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public TrieMap<String, WordCount> getWordCountMap() {
		return wordCountMap;
	}

	public void setWordCountMap(TrieMap<String, WordCount> wordCountMap) {
		this.wordCountMap = wordCountMap;
	}

	public FolderSearchTaskTrieMap(Folder folder, TrieMap<String, WordCount> wordCountMap) {
		super();
		this.folder = folder;
		this.wordCountMap = wordCountMap;
	}

	@Override
	protected void compute() {
		List<RecursiveAction> forks = new LinkedList<>();
		for(Document document : folder.getDocuments()) {
			DocumentSearchTaskTrieMap task = new DocumentSearchTaskTrieMap(document, wordCountMap);
			forks.add(task);
			task.fork();
		}
	}
}
