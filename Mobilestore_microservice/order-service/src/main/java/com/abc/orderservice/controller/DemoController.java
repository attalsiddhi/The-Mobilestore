package com.abc.orderservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class DemoController {

	@Value("${myname}")
	private String name;
	
	@Value("${message}")
	private String msg;
	
	@GetMapping("/data")
	public String getData() {
		
		return "my name is: "+name;
	}
	
	@GetMapping("/config/data")
	public String getDataFromConfigServer() {
		
		return "Data From Config server: "+msg;
	}
}
