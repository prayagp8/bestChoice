package com.bc.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.exception.AddressException;
import com.bc.model.Address;
import com.bc.repo.AddressRepo;
import com.bc.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepo aRepo;
	
	@Override
	public Address addAddress(Address address) throws AddressException {
		Address a =aRepo.save(address);
		if(a!=null) {
			return a;
		}else {
			throw new AddressException("Address not found");
		}
	}

	@Override
	public Address updateAddress(Address address) throws AddressException {
		Address a= aRepo.findById(address.getAddressId()).orElseThrow(()->new AddressException("Address not found"));
		aRepo.save(address);
		return a;
	}

	@Override
	public Address remove(Integer addressId) throws AddressException {
		Optional<Address> opt= aRepo.findById(addressId);
		if(opt.isPresent()) {
			Address a= opt.get();
			aRepo.delete(a);
			return a;
		}else {
			throw new AddressException("Address not found addressid - "+addressId);
		}
	}

	@Override
	public List<Address> viewAllAddress() throws AddressException {
		List<Address> addresses= aRepo.findAll();
		if(addresses.size()>0) {
			return addresses;
		}else {
			throw new AddressException("Address not found");
		}
	}

	@Override
	public Address viewAddress(Integer addressId) throws AddressException {
		Address a= aRepo.findById(addressId).orElseThrow(()->new AddressException("Address not found"));
		return a;
	}
	

}
