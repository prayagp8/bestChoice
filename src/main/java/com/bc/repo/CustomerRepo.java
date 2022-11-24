package com.bc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bc.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
