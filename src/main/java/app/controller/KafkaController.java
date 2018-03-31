package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.kafka.Consumer;
import app.kafka.Producer;

@RestController
public class KafkaController {

	@Autowired
	private Producer producer;

	@Autowired
	private Consumer consumer;

	@RequestMapping("/producer")
	public void produce(@RequestParam(value = "message", defaultValue = "test") String message) {
		producer.produce(message);
	}

	@RequestMapping("/consumer")
	public void consume() {
		consumer.consume();
	}

}
