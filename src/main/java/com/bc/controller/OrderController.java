package com.bc.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bc.exception.CustomerException;
import com.bc.exception.OrderException;
import com.bc.model.Orders;
import com.bc.service.OrderService;

@RestController
public class OrderController {
	
	
	private OrderService oService;
	
	@PostMapping("/orders")
	public ResponseEntity<Orders> addOrder(@RequestParam("customerId") Integer cid) throws OrderException,CustomerException{
		return new ResponseEntity<Orders>(oService.addOrder(cid),HttpStatus.CREATED);
	}
	
	@PutMapping("/orders")
	public ResponseEntity<Orders> updateOrder(@RequestBody Orders order)throws OrderException{
		return new ResponseEntity<Orders>(oService.updateOrder(order),HttpStatus.OK);
	}
	
	@GetMapping("/order")
	public ResponseEntity<Orders> viewOrder(@RequestParam("orderId") Integer orderId) throws OrderException{
		return new ResponseEntity<Orders>(oService.viewOrder(orderId),HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Orders>> viewAllOrder() throws OrderException{
		return new ResponseEntity<List<Orders>>(oService.viewAllOrder(),HttpStatus.OK);
	}
	
//	public List<Orders> viewAllOrdersByLocation(String city) throws OrderException;
	
	@GetMapping("/ordersbyuser")
	public ResponseEntity<List<Orders>> viewAllOrdersByUserId(@RequestParam("userId") Integer userId) throws OrderException{
		return new ResponseEntity<List<Orders>>(oService.viewAllOrdersByUserId(userId),HttpStatus.OK);
	}	
}
