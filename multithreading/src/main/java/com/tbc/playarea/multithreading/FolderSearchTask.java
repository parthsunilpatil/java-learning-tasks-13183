package com.tbc.playarea.multithreading;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveAction;

public class FolderSearchTask extends RecursiveAction {
	
	private Folder folder;
	private ConcurrentHashMap<String, WordCount> wordCountMap;

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

	public ConcurrentHashMap<String, WordCount> getWordCountMap() {
		return wordCountMap;
	}

	public void setWordCountMap(ConcurrentHashMap<String, WordCount> wordCountMap) {
		this.wordCountMap = wordCountMap;
	}

	public FolderSearchTask(Folder folder, ConcurrentHashMap<String, WordCount> wordCountMap) {
		super();
		this.folder = folder;
		this.wordCountMap = wordCountMap;
	}

	@Override
	protected void compute() {
		List<RecursiveAction> forks = new LinkedList<>();
		for(Document document : folder.getDocuments()) {
			DocumentSearchTask task = new DocumentSearchTask(document, wordCountMap);
			forks.add(task);
			task.fork();
		}
	}
}
