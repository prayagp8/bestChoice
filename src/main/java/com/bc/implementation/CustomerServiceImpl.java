package com.bc.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.exception.CustomerException;
import com.bc.model.Customer;
import com.bc.repo.CustomerRepo;
import com.bc.service.CustomerService;

@Service
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
		Optional<Customer> opt=cRepo.findById(customerId);
		if(opt.isPresent()) {
			Customer c=opt.get();
			cRepo.delete(c);
			return c;
		}else {
			throw new CustomerException("Customer not found with cid - "+customerId);
		}
		
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
