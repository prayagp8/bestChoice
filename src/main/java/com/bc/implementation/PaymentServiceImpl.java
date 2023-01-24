package com.bc.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.exception.BillException;
import com.bc.exception.WalletException;
import com.bc.model.Bill;
import com.bc.model.Orders;
import com.bc.model.Wallet;
import com.bc.repository.BillRepo;
import com.bc.repository.OrderRepo;
import com.bc.repository.WalletRepo;
import com.bc.service.PaymentService;
@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private BillRepo billRepo;
	
	@Autowired
	private WalletRepo walletRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	

	@Override
	public String payment(Integer billId,Integer walletId) throws BillException,WalletException {
		Bill b = billRepo.findById(billId).orElseThrow(()-> new BillException("Invalid bill id"));
		
		Wallet w =  walletRepo.findById(walletId).orElseThrow(()-> new WalletException("wallet id invalid"));
		
		if(w.getWalletBalance()<b.getAmount()) {
			throw new WalletException("Insufficiant Balance!! Add RS : " + (b.getAmount()-w.getWalletBalance())+" amount in your wallet");
		}else {
			double balance = w.getWalletBalance()- b.getAmount();
			
			w.setWalletBalance(balance);
			walletRepo.save(w);
			Orders o = orderRepo.findById(b.getOrderId()).orElseThrow(()-> new BillException("order not found!"));
			o.setOrderStatus("order placed!");
			orderRepo.save(o);
			
			return "payment done successfully "+"Remaining balance :"+balance; 
			
		}
		
		
		
	}

	@Override
	public Wallet recharge(Integer walletId,Double amount) throws WalletException {
		Wallet w =  walletRepo.findById(walletId).orElseThrow(()-> new WalletException("wallet id invalid"));
		w.setWalletBalance(w.getWalletBalance()+amount);
		walletRepo.save(w);
		return w;
	}

}
