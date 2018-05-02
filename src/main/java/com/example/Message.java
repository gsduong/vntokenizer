package com.example;

import java.util.ArrayList;
import java.util.List;

import ai.vitk.tok.Tokenizer;
import ai.vitk.type.Token;

public class Message {
	private String originalText;
	private String tokenizedText;
	private String finalText;
	private List<String> tokens;
	private int size;

	public Message(String _originalText) {
		this.originalText = _originalText;
		this.tokens = new ArrayList<String>();
		this.tokenize();
		this.clean();
		this.size = this.tokens.size();
	}

	public String getOriginalText() {
		return this.originalText;
	}

	private void tokenize() {
		StringBuffer buffer = new StringBuffer();
		Tokenizer tokenizer = new Tokenizer();
		List<Token> words = tokenizer.tokenize(this.originalText.toLowerCase());
		for (Token token : words) {
			tokens.add(token.getWord().replace(" ", "_"));
			buffer.append(token.getWord().replace(" ", "_") + " ");
		}
		this.tokenizedText = buffer.toString().trim();
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
		StopWordsService service = new StopWordsService();

		for (String string : this.tokens) {
			if (service.getStopWords().contains(string)) {
				toRemove.add(string);
			}
		}

		this.tokens.removeAll(toRemove);
		StringBuffer buffer = new StringBuffer();
		for (String string : this.tokens) {
			buffer.append(string + " ");
		}
		this.finalText = buffer.toString().trim();

	}

	public String getTokenizedText() {
		return this.tokenizedText;
	}

	public String getFinalText() {
		return this.finalText;
	}

	public int getTokensSize() {
		return this.size;
	}

}
