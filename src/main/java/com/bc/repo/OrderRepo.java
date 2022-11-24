package com.bc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bc.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{
	
	@Query("select * from Order o where o.Address.city=?1")
	public List<Order> getAllOrdersByLocation(String location);
	
	@Query("select * from Order o where o.Customer.cId=?1")
	public List<Order> getAllOrdersByCid(Integer cId);
}
