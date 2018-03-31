package app.kafka;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@Value("${topic.name}")
	private String topic;

	@Value("${bootstrap.servers}")
	private String bootstrapServers;

	@Value("${group.id}")
	private String groupId;

	public void consume() {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", bootstrapServers);
		properties.put("group.id", groupId);
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
		kafkaConsumer.subscribe(Arrays.asList(topic));

		try {
			while (true) {
				ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
				Iterator<?> it = records.iterator();
				while (it.hasNext()) {
					ConsumerRecord<?, ?> record = (ConsumerRecord<?, ?>) it.next();
					System.out.println(String.format("Consuming: Topic - %s, Partition - %d, Key: %s, Value: %s",
							record.topic(), record.partition(), record.key(), record.value()));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			kafkaConsumer.close();
		}
	}
}
