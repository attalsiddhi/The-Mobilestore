package com.abc.orderservice.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MobileDTO {

	private int mobileId;
	private String mobileName;
	private Double price;
	private LocalDate mfd;
	private String companyName;
}
