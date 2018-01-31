package com.tbc.playarea.multithreading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvReaderThread implements Runnable {
	
	private String fileName;
	private int numberOfLines;

	public CsvReaderThread(String fileName, int numberOfLines) {
		super();
		this.fileName = fileName;
		this.numberOfLines = numberOfLines;
	}

	public void run() {
		File file = new File(fileName);
		System.out.println("Thread :: " + Thread.currentThread().getName() + " :: Reader Thread on file: " + file.getAbsolutePath() + " started at " + System.currentTimeMillis());
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder("");
		String line = "";
		int i = 0, I = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null) {
				if(sb.length() == 0) {
					sb.append(line);
				} else {
					sb.append("\n").append(line);
				}
				if(++i == numberOfLines) {
					new Thread(new CsvWriterThread(sb.toString(), I++, "/home/parthp/split_csv_data/")).start();
					i = 0;
					sb.setLength(0);
				}
			}
			System.out.println("Thread :: " + Thread.currentThread().getName() + " :: completed at " + System.currentTimeMillis());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

}
