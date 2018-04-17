package app.controller;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.model.Greeting;
import app.model.LogDTO;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	private static final Logger logger = LogManager.getLogger(GreetingController.class);

	@RequestMapping("/greeting")

	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

		LogDTO test = new LogDTO();
		test.setKey(UUID.randomUUID().toString());
		test.setValue(name);
		ObjectMapper mapper = new ObjectMapper();

		try {
			logger.info(mapper.writeValueAsString(test));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return new Greeting(counter.incrementAndGet(), String.format(template, name));

	}

}
