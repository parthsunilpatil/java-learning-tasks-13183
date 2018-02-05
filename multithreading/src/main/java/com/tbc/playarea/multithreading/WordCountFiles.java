package com.tbc.playarea.multithreading;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class WordCountFiles {
	public static void main(String[] args) {
		try (Stream<Path> paths = Files.walk(Paths.get("/home/parthp/wiki_searches/"));) {
			paths.filter(Files::isRegularFile).forEach(t -> {
				try {
					List<String> lines = Files.readAllLines(t);
					lines.forEach(System.out::println);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
