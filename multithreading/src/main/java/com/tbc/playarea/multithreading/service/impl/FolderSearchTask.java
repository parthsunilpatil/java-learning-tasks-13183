package com.tbc.playarea.multithreading.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveAction;

import com.tbc.playarea.multithreading.WordCount;
import com.tbc.playarea.multithreading.dao.Document;
import com.tbc.playarea.multithreading.dao.Folder;
import com.tbc.playarea.multithreading.service.BasicSearchTask;

public class FolderSearchTask extends BasicSearchTask {
	
	private Folder folder;

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

	public FolderSearchTask(Folder folder, ConcurrentHashMap<String, WordCount> wordCountMap) {
		super(wordCountMap);
		this.folder = folder;
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
