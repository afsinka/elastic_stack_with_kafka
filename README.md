# elastic_stack_with_kafka

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

