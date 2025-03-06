package com.abc.mobilestore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.mobilestore.entity.Cart;
import com.abc.mobilestore.entity.CartItem;
import com.abc.mobilestore.entity.Customer;
import com.abc.mobilestore.entity.Order;
import com.abc.mobilestore.entity.OrderItem;
import com.abc.mobilestore.exception.ResourceNotFoundException;
import com.abc.mobilestore.repository.CartItemRepository;
import com.abc.mobilestore.repository.CartRepository;
import com.abc.mobilestore.repository.OrderRepository;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	
	
	@Override
	public Order placeOrder(int customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		
		Cart cart = customer.getCart();
		
		List<CartItem> cartItems = cart.getCartItems();
		
		Order order = new Order();
//		order.setOrderId(0);
		order.setOrderAmount(cart.getCartTotal());
		order.setOrderStatus("Pending");
		order.setOrderDate(LocalDate.now());
		order.setCustomer(customer);
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(CartItem item: cartItems) {
			OrderItem orderItem = new OrderItem();
//			orderItems.setOrderItemId(0);
			orderItem.setItemTotal(item.getItemTotal());
			orderItem.setMobileId(item.getMobileId());
			orderItem.setQty(item.getQty());
			orderItem.setOrder(order);
			
			orderItems.add(orderItem);
//			orderItemRepository.save(OrderItem);
		}
		
		order.setOrderItems(orderItems);
		orderRepository.save(order);
		
//		Clear the cart Data
		for(CartItem item: cartItems) {
//			CartItem cItem = cartItemRepository.findById(item.getCartItemId()).get();
//			cartItemRepository.delete(cItem);
			cartItemRepository.delete(item);
		}
		cart.setCartItems(null);
//		Rest The cart Total
		cart.setCartTotal(0);
//		Update the cart
		cartRepository.save(cart);
		
		
		return order;
	}

	@Override
	public Order getOrderDetails(int orderId) {

		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new ResourceNotFoundException("Order not found with id"+orderId);
		}
		Order order = optionalOrder.get();
		return order;
	}

}
