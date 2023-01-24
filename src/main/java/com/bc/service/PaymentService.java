package com.bc.service;

import org.springframework.stereotype.Service;

import com.bc.exception.BillException;
import com.bc.exception.WalletException;
import com.bc.model.Wallet;

public interface PaymentService {

	
	public String payment(Integer billId,Integer walletId) throws BillException, WalletException;
	
	public Wallet recharge(Integer walletId,Double amount)throws WalletException;
}
