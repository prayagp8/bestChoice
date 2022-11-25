package com.bc.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
@Entity
@Data
public class Product {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	private String productName;
	private Double price ;
	private String color;
	private String dimension;
	private String specification;
	private String menufacturer;
	private Integer quantity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
}
