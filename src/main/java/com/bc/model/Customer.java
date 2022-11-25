package com.bc.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Address> addressList = new HashSet<>();
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	private List<Order> orders;
	
}
