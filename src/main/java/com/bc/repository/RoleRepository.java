package com.bc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bc.model.ERole;
import com.bc.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
