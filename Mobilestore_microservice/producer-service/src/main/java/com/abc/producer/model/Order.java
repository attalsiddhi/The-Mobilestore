package com.abc.producer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Order {

	private int orderId;
	private double orderAmount;
	private String customerName;
	private String email;
	private String mobile;
}
