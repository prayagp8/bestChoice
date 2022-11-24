package com.bc.model;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
@Data
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cId;
	private String fName;
	private String lName;
	private String mobile;
	private String email;
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Address> addressList = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;
	
	
	
}
