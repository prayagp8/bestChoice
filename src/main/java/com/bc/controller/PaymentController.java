package com.bc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bc.exception.BillException;
import com.bc.exception.WalletException;
import com.bc.model.Wallet;

import com.bc.service.PaymentService;

@RestController
@RequestMapping("/api/payment/")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;


	@PostMapping("/")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> payment(@RequestParam Integer billId,@RequestParam Integer walletId) throws BillException, WalletException{
		return new ResponseEntity<String>(paymentService.payment(billId, walletId),HttpStatus.OK);
	};

	@PostMapping("/recharge")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Wallet> recharge(@RequestParam Integer walletId,@RequestParam Double amount)throws WalletException{
		return new ResponseEntity<Wallet>(paymentService.recharge(walletId, amount),HttpStatus.OK);
	};

}
