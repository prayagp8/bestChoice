package com.bc.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.exception.AddressException;
import com.bc.exception.CustomerException;
import com.bc.model.Address;
import com.bc.model.Customer;
import com.bc.repository.AddressRepo;
import com.bc.repository.CustomerRepo;
import com.bc.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private CustomerRepo customerRepo;

	// check customer is available or not in database
	public Customer userValidation(Integer userId) throws CustomerException {
		Optional<Customer> customerOpt = customerRepo.findById(userId);
		if (customerOpt.isEmpty())
			throw new CustomerException("Customer not found with this id " + userId);
		return customerOpt.get();
	}

	@Override
	public Address updateAddress(Address address, Integer userId) throws AddressException, CustomerException {
		if (address == null)
			throw new AddressException("Address can't be null");
		Customer customer = userValidation(userId);
		customer.setAddress(address);
		customerRepo.save(customer);
		return address;
	}

	@Override
	public List<Address> viewAllAddress() throws AddressException {
		List<Address> addresses = addressRepo.findAll();
		if (addresses.isEmpty())
			throw new AddressException("Address not found!");
		return addresses;
	}

	@Override
	public Address viewAddressByUserId(Integer userId) throws CustomerException {
		Customer customer = userValidation(userId);
		return customer.getAddress();
	}

}
