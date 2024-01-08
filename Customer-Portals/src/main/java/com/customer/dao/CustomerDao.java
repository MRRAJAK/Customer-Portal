package com.customer.dao;

import com.customer.entities.Customer;

public interface CustomerDao {

	Customer findByCustomerId(Long customerId);

	Customer createCustomer(Customer customer);

	Customer findByEmail(String email);

}	
