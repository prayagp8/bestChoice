package com.bc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bc.model.Customer;
import com.bc.model.Orders;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	@Query("select c.orders from Customer c where c.id=?1")
	public List<Orders> getAllOrderByid(Long cId);

	public Customer findByEmail(String email);

	Optional<Customer> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
