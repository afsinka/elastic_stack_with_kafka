package app.kafka;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	@Value("${topic.name}")
	private String topic;

	@Value("${bootstrap.servers}")
	private String bootstrapServers;

	private final AtomicLong counter = new AtomicLong();
	private Properties properties = new Properties();
	private KafkaProducer<String, String> kafkaProducer;

	@PostConstruct
	private void init() {
		properties.put("bootstrap.servers", bootstrapServers);
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		kafkaProducer = new KafkaProducer<String, String>(properties);
	}

	public void produce(final String message) {

		try {

			String key = Long.toString(counter.incrementAndGet());
			String value = message;
			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, key, value);
			kafkaProducer.send(producerRecord);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// kafkaProducer.close();
		}
	}
}
