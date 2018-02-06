package com.tbc.playarea.multithreading.csv.splitter;

public class CsvFileSplitter {
	public static void main(String[] args) {
		CsvReaderThread reader = new CsvReaderThread(
				"/home/parthp/Downloads/Multithreading_Task1_Books.csv",
				"/home/parthp/csv_file_split_output/",
				1000);
		new Thread(reader).start();
	}
}
