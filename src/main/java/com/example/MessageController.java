package com.example;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@RequestMapping(value = "/message", method = RequestMethod.POST, consumes = "application/json")
	public Message getMessage(@RequestBody TextMessage textMsg) {
		return new Message(textMsg.getMessage());
	}
}
