package com.bc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bc.model.Wallet;

public interface WalletRepo extends JpaRepository<Wallet, Integer> {

	
}
