package com.tbc.playarea.multithreading.wiki.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class WikiHttpClient implements Runnable {
	
	private String wikiKey;
	private String outputFolderName;
	
	public WikiHttpClient(String wikiKey, String outputFolderName) {
		super();
		this.wikiKey = wikiKey;
		this.outputFolderName = outputFolderName;
	}

	private String getJsonKeyRecur(String key, JsonObject jsonObj) {
		for(Entry<String, JsonValue> e : jsonObj.entrySet()) {
			if(key.equals(e.getKey())) return e.getValue().toString();
			else if(e.getValue().getValueType().equals(JsonValue.ValueType.OBJECT)) return getJsonKeyRecur(key, e.getValue().asJsonObject());
		}
		return null;
	}

	@Override
	public void run() {
			URL url;
			try {
				url = new URL("https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=" + wikiKey);
				HttpURLConnection request = (HttpURLConnection) url.openConnection();
				request.connect();

				JsonObject jsonObj = Json.createReader(new InputStreamReader((InputStream) request.getContent())).readObject();
				String extract = getJsonKeyRecur("extract", jsonObj);
				extract = extract.substring(1, extract.length() - 1).replace("\\n", System.lineSeparator());
				String outputFileName = outputFolderName + "Document_" + Thread.currentThread().getName() + ".txt";
				try (
						FileWriter fileWriter = new FileWriter(new File(outputFileName), true); 
						PrintWriter writer = new PrintWriter(fileWriter);
					) {
					writer.println(extract);
					System.out.println("Thread :: " + Thread.currentThread().getName() + " :: completed at: " + System.currentTimeMillis());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//} catch (Exception e) {
	}
}