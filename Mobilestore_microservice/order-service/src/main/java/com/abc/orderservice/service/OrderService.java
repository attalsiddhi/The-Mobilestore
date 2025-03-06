package com.abc.orderservice.service;

import java.util.List;

import com.abc.orderservice.entity.Order;
import com.abc.orderservice.entity.OrderItem;
import com.abc.orderservice.model.InvoiceDTO;

public interface OrderService {
	
	Order placeOrder(int customerId, List<OrderItem> orderItems);
	
	Order getOrderDetails(int orderId);
	
	InvoiceDTO generateInvoice(int orderId);
	
}
