package com.tbc.playarea.multithreading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvReaderThread implements Runnable {
	
	private String fileName;
	private String outputFolderName;
	private int numberOfLines;

	public CsvReaderThread(String fileName, String folderName, int numberOfLines) {
		super();
		this.fileName = fileName;
		this.numberOfLines = numberOfLines;
		this.outputFolderName = folderName;
	}

	public void run() {
		File file = new File(fileName);
		System.out.println("Thread :: " + Thread.currentThread().getName() + " :: Reader Thread on file: " + file.getAbsolutePath() + " started at " + System.currentTimeMillis());
		StringBuilder sb = new StringBuilder("");
		String line = "";
		int lineCount = 0, written = 0, actual = 0;
		try (
				BufferedReader br = new BufferedReader(new FileReader(file));
			) {
			//ExecutorService executorService = Executors.newCachedThreadPool();
			line = br.readLine();
			while(line != null) {
				if(sb.length() == 0) {
					sb.append(line);
				} else {
					sb.append("\n").append(line);
				}
				actual++;
				lineCount++;
				if(lineCount == numberOfLines) {
					written += numberOfLines;
					new Thread(new CsvWriterThread(sb.toString(), outputFolderName)).start();
					lineCount = 0;
					sb.setLength(0);
				}
				line = br.readLine();
				if(line == null && lineCount < numberOfLines) {
					written += lineCount;
					new Thread(new CsvWriterThread(sb.toString(), outputFolderName)).start();
				}
			}
			System.out.println("Lines Read: " + actual + " :: Lines Written: " + written);
			System.out.println("Thread :: " + Thread.currentThread().getName() + " :: completed at " + System.currentTimeMillis());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
