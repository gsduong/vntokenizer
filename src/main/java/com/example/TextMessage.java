package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TextMessage {
	private String message;

	@JsonCreator
	public TextMessage(@JsonProperty("message") String message) {
		this.message = message;
	}

	@JsonProperty("message")
	public String getMessage() {
		return this.message;
	}
}
