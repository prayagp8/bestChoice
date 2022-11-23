package com.bc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Address {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;
	private String streetNo;
	private String buildingName;
	private String city;
	private String state;
	private String country;
	private String pincode;
	
	

}
