package com.customer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.dao.CustomerDao;
import com.customer.entities.Customer;
import com.customer.repository.CustomerRepository;
@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private CustomerRepository customerRepository;
	

	@Override
	public Customer findByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return customerRepository.findById(customerId).orElseThrow();
	}


	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}


	@Override
	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		return customerRepository.findByEmail(email);
	}

}
