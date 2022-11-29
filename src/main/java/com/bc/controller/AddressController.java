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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bc.exception.AddressException;
import com.bc.model.Address;
import com.bc.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService aService;

	@PostMapping("/add")
	public ResponseEntity<Address> addNewAddress(@RequestBody Address address) throws AddressException {
		return new ResponseEntity<Address>(aService.addAddress(address), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address) throws AddressException {
		return new ResponseEntity<Address>(aService.updateAddress(address), HttpStatus.OK);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Address> removeAddressById(@PathVariable("id") Integer addressId) throws AddressException {
		return new ResponseEntity<Address>(aService.remove(addressId), HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<List<Address>> viewAllAddress() throws AddressException {
		return new ResponseEntity<List<Address>>(aService.viewAllAddress(), HttpStatus.OK);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<Address> viewAddressById(@PathVariable("id") Integer addressId) throws AddressException {
		return new ResponseEntity<Address>(aService.viewAddress(addressId), HttpStatus.OK);
	}
}
