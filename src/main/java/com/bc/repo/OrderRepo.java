package com.bc.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bc.model.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer>{
	
//	@Query("select o from Orders o inner join Address a on o.address_address_id=a.address_id where a.city=?1")
//	List<Orders> getAllOrdersByLocation(String city);
	
	
}
