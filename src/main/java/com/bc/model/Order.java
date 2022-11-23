package com.bc.model;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.processing.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
@Entity
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private LocalDate date;
	private String orderStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> productList;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
}
