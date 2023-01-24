package com.bc.service;

import java.util.List;

import com.bc.exception.BillException;
import com.bc.exception.OrderException;
import com.bc.model.Bill;

public interface BillService {

	public Bill generateBill(Integer orderId) throws OrderException;
	
	public Bill viewBill(Integer billId) throws BillException;
	
	public List<Bill> viewallBills()throws BillException;  
}
