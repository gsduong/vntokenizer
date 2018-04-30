package com.example;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

public class CustomFileReader {
	private List<String> stopwords;

	public CustomFileReader() {
		stopwords = new ArrayList<String>();
		// this.readFromClassPath();
		this.read();
	}

	private void read() {
		Path path = null;
		try {
			path = Paths.get(getClass().getClassLoader().getResource("/stopwords.txt").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Stream<String> lines = null;
		try {
			lines = Files.lines(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lines.forEach(line -> stopwords.add(line));
		lines.close();
	}

	public List<String> getStopWords() {
		return this.stopwords;
	}

	private void readFromClassPath() {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("stopwords.txt").getFile());
		try {
			@SuppressWarnings("deprecation")
			String data = FileUtils.readFileToString(file);
			String[] array = data.split("\\s+");
			for (String string : array) {
				this.stopwords.add(string);
				System.out.println(string);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
