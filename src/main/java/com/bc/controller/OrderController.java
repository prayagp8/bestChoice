package com.bc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bc.exception.BillException;
import com.bc.exception.CartException;
import com.bc.exception.CustomerException;
import com.bc.exception.OrderException;
import com.bc.model.Bill;
import com.bc.model.Orders;
import com.bc.service.BillService;
import com.bc.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService oService;

	@Autowired
	private BillService billService;

	@PostMapping("/add")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Orders> addOrder(@RequestParam("customerId") Long customerId)
			throws OrderException, CustomerException, CartException {
		return new ResponseEntity<Orders>(oService.addOrder(customerId), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Orders> updateOrder(@RequestBody Orders order) throws OrderException {
		return new ResponseEntity<Orders>(oService.updateOrder(order), HttpStatus.OK);
	}

	@GetMapping("/viewbyorderid/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Orders> viewOrderById(@PathVariable("id") Integer orderId) throws OrderException {
		return new ResponseEntity<Orders>(oService.viewOrder(orderId), HttpStatus.OK);
	}

	@GetMapping("/views")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Orders>> viewAllOrder() throws OrderException {
		return new ResponseEntity<List<Orders>>(oService.viewAllOrder(), HttpStatus.OK);
	}

	@GetMapping("/view/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Orders>> viewOrderByUserId(@PathVariable("userId") Long userId)
			throws OrderException {
		return new ResponseEntity<List<Orders>>(oService.viewAllOrdersByUserId(userId), HttpStatus.OK);
	}

	@GetMapping("/bill")
	public ResponseEntity<Bill> generateBill(@RequestParam Integer orderId) throws OrderException{
		return new ResponseEntity<Bill>(billService.generateBill(orderId),HttpStatus.OK);
	};

	@GetMapping("/viewbill/{bId}")
	public ResponseEntity<Bill> viewBill(@PathVariable("bId") Integer billId) throws BillException{
		return new ResponseEntity<Bill>(billService.viewBill(billId),HttpStatus.OK);
	};

	@GetMapping("/bills")
	public ResponseEntity< List<Bill>> viewallBills()throws BillException{
		return new ResponseEntity<List<Bill>>(billService.viewallBills(),HttpStatus.OK);
	};  
}
