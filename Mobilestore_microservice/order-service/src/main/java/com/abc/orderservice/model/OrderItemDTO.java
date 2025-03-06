package com.abc.orderservice.model;

import lombok.Getter;

import lombok.Setter;

@Setter
@Getter
public class OrderItemDTO {

	private int itemId;
	private MobileDTO mobile;
	private int qty;
	private Double itemTotal;
}
