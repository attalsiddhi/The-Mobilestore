package com.abc.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.producer.service.ProducerService;

@RestController
@RequestMapping("/api/producer")
public class MessageController {

	@Autowired
	private  ProducerService producerService;
	
	@PostMapping("/publish")
	public ResponseEntity<String> sendMsgToServer(@RequestBody String msg){
		producerService.sendMessage(msg);
		
		return new ResponseEntity<>("Messsage Published",HttpStatus.OK);
	}
	
//	@PostMapping("/checkout")
//	public ResponseEntity<String> sendMessageToServer(@RequestBody String msg){
//		producerService.sendCheckoutMessage(msg);
//		
//		return new ResponseEntity<>("Messsage Published in Checkout",HttpStatus.OK);
//	}
}
