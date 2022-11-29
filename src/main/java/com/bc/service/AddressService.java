
package com.bc.service;

import java.util.List;

import com.bc.exception.AddressException;
import com.bc.exception.CustomerException;
import com.bc.model.Address;

public interface AddressService {

	public Address updateAddress(Address address, Integer userId) throws AddressException, CustomerException;

	public List<Address> viewAllAddress() throws AddressException;

	public Address viewAddressByUserId(Integer userId) throws CustomerException;

}
