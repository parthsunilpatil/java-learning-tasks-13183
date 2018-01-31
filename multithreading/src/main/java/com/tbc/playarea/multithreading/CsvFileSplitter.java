package com.tbc.playarea.multithreading;

public class CsvFileSplitter {
	public static void main(String[] args) {
		CsvReaderThread reader = new CsvReaderThread("/home/parthp/Downloads/SampleCSVFile_119kb.csv", 100);
		new Thread(reader).start();
	}
}
