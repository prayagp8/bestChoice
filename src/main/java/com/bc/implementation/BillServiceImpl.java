package com.bc.implementation;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.exception.BillException;
import com.bc.exception.OrderException;
import com.bc.model.Bill;
import com.bc.model.Orders;
import com.bc.model.Product;
import com.bc.repository.BillRepo;
import com.bc.repository.OrderRepo;
import com.bc.service.BillService;


@Service
public class BillServiceImpl implements BillService  {

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	BillRepo billRepo;

	@Override
	public Bill generateBill(Integer orderId) throws OrderException {

		
		List<Bill> bills = billRepo.findAll();
		for(int i=0;i<bills.size();i++) {
			if(bills.get(i).getOrderId()==orderId) {
				throw new OrderException("Billing done already for orderId : "+ orderId);
			}
		}
		
		Optional<Orders> opt =  orderRepo.findById(orderId);

		if(opt.isPresent()) {
			Bill bill = new Bill();
			Orders o  = opt.get();
			List<Product> ls = o.getProductList();
			double amount = 0;

			for(int i=0;i<ls.size();i++) {
				amount+=ls.get(i).getPrice();
			}
			bill.setAddress(o.getAddress().getCity());
			bill.setAmount(amount);
			bill.setQuantity(o.getProductList().size());
			bill.setOrderId(o.getOrderId());
			o.setOrderStatus("order placed !");

			orderRepo.save(o);

			return billRepo.save(bill);




		}else {
			throw new OrderException("Invalid order id!");
		}

	}

	@Override
	public Bill viewBill(Integer billId) throws BillException {
		Bill b = billRepo.findById(billId).orElseThrow(()-> new BillException("No bill found for this bill id : "+ billId));
		return b;
	}

	@Override
	public List<Bill> viewallBills() throws BillException {
		List<Bill> bills = billRepo.findAll();
		
		if(bills.size()!=0) {
			return bills;
		}else {
			throw new BillException("No bill record is available!");
		}
		
	}

}
