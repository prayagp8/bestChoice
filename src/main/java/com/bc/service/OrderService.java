package com.bc.service;

import java.util.List;

import com.bc.exception.OrderException;
import com.bc.model.Order;

public interface OrderService {
	
	
	public Order addOrder(Order order) throws OrderException;
	
	public Order updateOrder(Order order)throws OrderException;
	
	public Order viewOrder(Integer orderId) throws OrderException;
	
	public List<Order> viewAllOrder() throws OrderException;
	
//	public List<Order> viewAllOrdersByLocation(String location) throws OrderException;
	
	public List<Order> viewAllOrdersByUserId(Integer userId) throws OrderException;

}
