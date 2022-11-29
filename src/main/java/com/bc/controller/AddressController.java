package com.bc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bc.exception.AddressException;
import com.bc.exception.CustomerException;
import com.bc.model.Address;
import com.bc.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PutMapping("/update/{userId}")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable("userId") Integer userId)
			throws AddressException, CustomerException {
		return new ResponseEntity<Address>(addressService.updateAddress(address, userId), HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<List<Address>> viewAllAddress() throws AddressException {
		return new ResponseEntity<List<Address>>(addressService.viewAllAddress(), HttpStatus.OK);
	}

	@GetMapping("/view/{userId}")
	public ResponseEntity<Address> viewAddressByUserId(@PathVariable("userId") Integer userId)
			throws CustomerException {
		return new ResponseEntity<Address>(addressService.viewAddressByUserId(userId), HttpStatus.OK);
	}
}
