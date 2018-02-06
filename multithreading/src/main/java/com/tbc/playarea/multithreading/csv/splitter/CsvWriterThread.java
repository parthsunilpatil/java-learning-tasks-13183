package com.tbc.playarea.multithreading.csv.splitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvWriterThread implements Runnable {
	
	private String lines;
	private String outputFolderName;
	
	public CsvWriterThread(String lines, String outputFolderName) {
		super();
		this.lines = lines;
		this.outputFolderName = outputFolderName;
	}

	public void run() {
		String outputFileName = outputFolderName + "SplitCsv_" + Thread.currentThread().getName() + ".csv";
		System.out.println("Thread :: " + Thread.currentThread().getName() + " :: Writer Thread on file: " + outputFileName + " started at: " + System.currentTimeMillis());
		try (
				FileWriter fileWriter = new FileWriter(new File(outputFileName)); 
				PrintWriter writer = new PrintWriter(fileWriter);
			) {
			writer.println(lines);
			System.out.println("Thread :: " + Thread.currentThread().getName() + " :: completed at: " + System.currentTimeMillis());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
