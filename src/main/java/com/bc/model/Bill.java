package com.bc.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;


@Data
@Entity
public class Bill{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billId;
	private String address;
	private int OrderId;
	private Integer Quantity;
	private LocalDate date = LocalDate.now();
	private double amount;
	
	
	
	
}
