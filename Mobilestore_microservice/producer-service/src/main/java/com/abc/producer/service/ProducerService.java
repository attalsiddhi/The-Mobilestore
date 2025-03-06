package com.abc.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String msg) {
		kafkaTemplate.send("payment-topic",msg);
	}
	
//	public void sendCheckoutMessage(String msg) {
//		kafkaTemplate.send("checkout-topic",msg);
//	}
}
