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
		this.size = this.tokens.size();
	}

	public String getOriginalText() {
		return this.originalText;
	}

	private void tokenize() {
		StringBuffer buffer = new StringBuffer();
		Tokenizer tokenizer = new Tokenizer();
		List<Token> words = tokenizer.tokenize(this.originalText.toLowerCase());
		List<String> toRemove = new ArrayList<String>();
		for (Token token : words) {
			if (token.getLemma().equals("PUNCT")) {
				toRemove.add(token.getWord());
			}
			tokens.add(token.getWord().replace(" ", "_"));
			buffer.append(token.getWord().replace(" ", "_") + " ");
		}
		this.tokenizedText = buffer.toString().trim();

		// Remove numbers
		// Remove punctuation
		for (String token : this.tokens) {
			if (token.matches(".*\\d+.*") || token.length() == 1) {
				toRemove.add(token);
			}
		}
		this.tokens.removeAll(toRemove);

		// Remove stopwords
		toRemove.clear();
		StopWordsService service = new StopWordsService();

		for (String string : this.tokens) {
			if (service.getStopWords().contains(string)) {
				toRemove.add(string);
			}
		}
		this.tokens.removeAll(toRemove);

		buffer = new StringBuffer();
		for (String string : this.tokens) {
			buffer.append(string + " ");
		}
		this.finalText = buffer.toString().trim();
		return;
	}

	public List<String> getTokens() {
		return this.tokens;
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
