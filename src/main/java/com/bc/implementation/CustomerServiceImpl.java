package com.bc.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bc.exception.CustomerException;
import com.bc.model.Customer;
import com.bc.repo.CustomerRepo;
import com.bc.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo cRepo;
	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		Customer c= cRepo.save(customer);
		if(c!=null) {
			return c;
		}else {
			throw new CustomerException("customer not added");
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		Customer c=cRepo.findById(customer.getCId()).orElseThrow(()->new CustomerException("Customer not found"));
		if(c!=null) {
			cRepo.save(customer);
		}
		return c;
	}

	@Override
	public Customer remove(Integer customerId) throws CustomerException {
		Customer c=cRepo.findById(customerId).orElseThrow(()->new CustomerException("Customer not found with customerid - "+customerId));
		if(c!=null) {
			cRepo.delete(c);
		}
		return c;
	}

	@Override
	public List<Customer> viewAllCustomer() throws CustomerException {
		List<Customer> customers=cRepo.findAll();
		if(customers.size()>0) {
			return customers;
		}else {
			throw new CustomerException("customer not found");
		}
	}

}
