package com.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(String email);

}
