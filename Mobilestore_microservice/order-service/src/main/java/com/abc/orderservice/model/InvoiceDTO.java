package com.abc.orderservice.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceDTO {

	private int orderId;
	private LocalDate orderDate;
	private Double orderAmount;
	private String OrderStatus;
//	Come From OrderItem Model class
	private List<OrderItemDTO> orderItems;
//	Come From Customer Model class
	private CustomerDTO customer;
	
}
