package com.bc.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
@Entity
@Data
public class Product {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	private String productName;
	private double price ;
	private String color;
	private String dimension;
	private String specification;
	private String menufacturer;
	private int quality;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;
}
