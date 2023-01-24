package com.bc.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.validator.constraints.CreditCardNumber;



import lombok.Data;

@Entity
@Data
public class PaymentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentId;
	private LocalDateTime time;
	
	
	@CreditCardNumber
	private String cardNo;
	

	

	

	

	



	
	
	



}
