package com.bc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bc.exception.CustomerException;
import com.bc.model.Customer;
import com.bc.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService cService;

	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer c) throws CustomerException {
		return new ResponseEntity<Customer>(cService.addCustomer(c), HttpStatus.OK);
	}

	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c) throws CustomerException {
		return new ResponseEntity<Customer>(cService.updateCustomer(c), HttpStatus.OK);
	}

	@DeleteMapping("/customer/{cId}")
	public ResponseEntity<Customer> remove(@PathVariable("cId") Integer cId) throws CustomerException {
		return new ResponseEntity<Customer>(cService.remove(cId), HttpStatus.OK);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewAllCustomer() throws CustomerException {
		return new ResponseEntity<List<Customer>>(cService.viewAllCustomer(), HttpStatus.OK);
	}

}
