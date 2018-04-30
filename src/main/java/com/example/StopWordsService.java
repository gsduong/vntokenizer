package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class StopWordsService {
	private Resource resource = new ClassPathResource("stopwords.txt");
	private List<String> stopwords;

	public StopWordsService() {
		this.stopwords = new ArrayList<String>();
		this.load(resource);
	}

	private void load(Resource resource) {
		try {
			Stream<String> stream = new BufferedReader(new InputStreamReader(resource.getInputStream())).lines();
			stream.forEach(line -> this.stopwords.add(line));
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> getStopWords() {
		return this.stopwords;
	}
}
