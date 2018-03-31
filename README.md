# elastic_stack_with_kafka

~~~~~~~~ UNDER DEVELOPMENT - NOT READY ~~~~~~~~

prerequisites:

	- zookeeper
	- apache kafka
	- elasticsearch
	- logstash
	- kibana

run: 
	mvn spring-boot:run
	
test:
	http://localhost:9099/greeting?name=John
	http://localhost:9099/consumer
	http://localhost:9099/producer?message=test

