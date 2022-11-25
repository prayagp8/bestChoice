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

import com.bc.exception.AddressException;
import com.bc.model.Address;
import com.bc.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService aService;
	
	@PostMapping("/address")
	public ResponseEntity<Address> addAddress(@RequestBody Address a) throws AddressException{
		
		return new ResponseEntity<Address>(aService.addAddress(a),HttpStatus.OK);
	}
	
	
	@PutMapping("/address")
	public ResponseEntity<Address> updateAddress(@RequestBody Address a) throws AddressException{
		
		return new ResponseEntity<Address>(aService.updateAddress(a),HttpStatus.OK);
	}
	
	@DeleteMapping("/address/{aId}")
	public ResponseEntity<Address> remove(@PathVariable("aId") Integer aId) throws AddressException{
		return new ResponseEntity<Address>(aService.remove(aId),HttpStatus.OK);
	}
	
	
	@GetMapping("/address")
	public ResponseEntity<List<Address>> viewAllAddress() throws AddressException{
		return new ResponseEntity<List<Address>>(aService.viewAllAddress(),HttpStatus.OK);
	}
	
	
	@GetMapping("/address/{aId}")
	public ResponseEntity<Address> viewAddress(@PathVariable("aId") Integer aId ) throws AddressException{
		return new ResponseEntity<Address>(aService.viewAddress(aId),HttpStatus.OK);
	}
}
