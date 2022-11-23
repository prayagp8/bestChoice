
package com.bc.service;
import java.util.List;

import com.bc.exception.AddressException;
import com.bc.model.Address;


public interface AddressService {

	public Address addAddress(Address address) throws AddressException;
	public Address updateAddress(Address address) throws AddressException;
	public Address remove(Integer addressId) throws AddressException;
	public List<Address> viewAllAddress() throws AddressException;
	public Address viewAddress(Integer addressId) throws AddressException;
	
	
}
