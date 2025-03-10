package com.abc.customerservice.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.customerservice.entity.Customer;
import com.abc.customerservice.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/loadtest")
	public String testLoadBalance() {
		return "Customer Service Running on Port: "+env.getProperty("local.server.port");
	}

	@PostMapping("/save")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> fetchCustomerDetails(@PathVariable int customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		
		System.out.println("Customer Service Running on Port"+env.getProperty("local.server.port"));
		
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> fetchAllCustomer() {
		List<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
}
