package com.customer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.constants.CustomerConstants;
import com.customer.dao.CustomerDao;
import com.customer.entities.Customer;
import com.customer.models.CustomerResponse;
import com.customer.models.Response;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public Customer findByCustomerId(Long customerId) {
		return customerDao.findByCustomerId(customerId);
	}

	public Response createCustomer(Customer customer) {
		CustomerResponse response = new CustomerResponse();
		Customer us = customerDao.createCustomer(customer);
		response.setCustomerId(us.getCustomerId());
		response.setErrorCode(CustomerConstants.CUSTOMER_SUCCESS_CODE);
		response.setErrorMessage(CustomerConstants.CUSTOMER_CREATED_SUCCESS);

		return response;

	}

	public Customer findByEmail(String email) {

		return customerDao.findByEmail(email);
	}

}
