package com.example;

import java.util.ArrayList;
import java.util.List;

import ai.vitk.tok.Tokenizer;
import ai.vitk.type.Token;

public class Message {
	private String originalText;
	private List<String> tokens;

	public Message(String _originalText) {
		this.originalText = _originalText;
		this.tokens = new ArrayList<String>();
		this.setTokens();
		this.clean();
	}

	public String getOriginalText() {
		return this.originalText;
	}

	public void setTokens() {
		Tokenizer tokenizer = new Tokenizer();
		List<Token> words = tokenizer.tokenize(this.originalText.toLowerCase());
		for (Token token : words) {
			tokens.add(token.getWord().replace(" ", "_"));
		}
		return;
	}

	public List<String> getTokens() {
		return this.tokens;
	}

	private void clean() {
		// Remove numbers
		// Remove punctuation
		List<String> toRemove = new ArrayList<String>();
		for (String token : this.tokens) {
			if (token.matches(".*\\d+.*") || token.length() == 1) {
				toRemove.add(token);
			}
		}

		this.tokens.removeAll(toRemove);
		toRemove.clear();

		// Remove stopwords
		CustomFileReader reader = new CustomFileReader();

		for (String string : this.tokens) {
			if (reader.getStopWords().contains(string)) {
				toRemove.add(string);
			}
		}

		this.tokens.removeAll(toRemove);
	}
}
