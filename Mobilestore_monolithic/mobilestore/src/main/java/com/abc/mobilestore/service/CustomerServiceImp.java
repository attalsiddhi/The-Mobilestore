package com.abc.mobilestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.mobilestore.entity.Cart;
import com.abc.mobilestore.entity.Customer;
import com.abc.mobilestore.exception.ResourceNotFoundException;
import com.abc.mobilestore.repository.CartRepository;
import com.abc.mobilestore.repository.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		customer = customerRepository.save(customer);
		
		Cart cart = new Cart();
//		cart.setCardId(0); 
		cart.setCartTotal(0);
//		cart.setCartItems(null); 
		cart.setCustomer(customer);
		
		cartRepository.save(cart);
		
		return customer;
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if(optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not found with id:"+customerId);
		}
		Customer customer = optionalCustomer.get();
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return customers;
	}

	@Override
	public Customer uodateCustomer(Customer customer) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getCustomerId());
		if(optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not found with id:"+customer.getCustomerId());
		}
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if(optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not found with id:"+customerId);
		}
		Customer customer = optionalCustomer.get();
		customerRepository.delete(customer);
	}

}
