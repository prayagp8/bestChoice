package com.bc.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.exception.OrderException;
import com.bc.exception.ProductException;
import com.bc.model.Order;
import com.bc.repo.CustomerRepo;
import com.bc.repo.OrderRepo;
import com.bc.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepo oRepo;
	
	@Autowired
	private CustomerRepo cRepo;
	@Override
	public Order addOrder(Order order) throws OrderException {
		 Order o= oRepo.save(order);
		 if(o!=null) {
			 return o;
		 }else {
			 throw new OrderException("Order not added");
		 }
	}

	@Override
	public Order updateOrder(Order order) throws OrderException {
		Order o=oRepo.findById(order.getOrderId()).orElseThrow(()-> new OrderException("Order not found"));
		if(o!=null) {
			oRepo.save(order);
		}
		return o;
	}

	@Override
	public Order viewOrder(Integer orderId) throws OrderException {
		Order o=oRepo.findById(orderId).orElseThrow(()-> new OrderException("Order not found"));
		return o;
	}

	@Override
	public List<Order> viewAllOrder() throws OrderException {
		List<Order> orders= oRepo.findAll();
		if(orders.size()>0) {
			return orders;
		}else {
			throw new OrderException("Order not found");
		}
	}

//	@Override
//	public List<Order> viewAllOrdersByLocation(String location) throws OrderException {
//		List<Order> orders= oRepo.getAllOrdersByLocation(location);
//		if(orders.size()>0) {
//			return orders;
//		}else {
//			throw new OrderException("Order not found");
//		}
//		
//	}

	@Override
	public List<Order> viewAllOrdersByUserId(Integer uderId) throws OrderException {
		List<Order> orders= cRepo.getAllOrderByCid(uderId);
		if(orders.size()>0) {
			return orders;
		}else {
			throw new OrderException("Order not found");
		}
	}

}
