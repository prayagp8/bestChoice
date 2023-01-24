package com.bc.model;

import java.time.LocalDate;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;



import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer feedbackId;
	private String feedback;
	
	@NotNull
	private Integer rating;
	
	private LocalDate submitDate;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	

	
	

}
