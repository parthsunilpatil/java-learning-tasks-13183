package com.tbc.playarea.multithreading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvWriterThread implements Runnable {
	
	private String lines;
	private int index;
	private String outputFolderName;
	
	public CsvWriterThread(String lines, int index, String outputFolderName) {
		super();
		this.lines = lines;
		this.index = index;
		this.outputFolderName = outputFolderName;
	}

	public void run() {
		String outputFileName = outputFolderName + "SplitCsv_" + index + ".csv";
		System.out.println("Thread :: " + Thread.currentThread().getName() + " :: Writer Thread on file: " + outputFileName + " started at: " + System.currentTimeMillis());
		PrintWriter writer = null;
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(new File(outputFileName), true);
			writer = new PrintWriter(fileWriter);
			writer.println(lines);
			System.out.println("Thread :: " + Thread.currentThread().getName() + " :: completed at: " + System.currentTimeMillis());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
