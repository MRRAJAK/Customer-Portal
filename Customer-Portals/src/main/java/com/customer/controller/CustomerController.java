package com.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.constants.CustomerConstants;
import com.customer.constants.CustomerGroup;
import com.customer.constants.Occupation;
import com.customer.entities.Customer;
import com.customer.models.Response;
import com.customer.services.CustomerService;
import com.customer.validation.Validation;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/create")
	public ResponseEntity<Response> createCustomer(@RequestBody Customer customer) {
		Response response = new Response();

		Customer custo = customerService.findByEmail(customer.getEmail());

		if (customer.getEmail().contains("@hikeon.tech")) {
			customer.setCustomerGroup("hikeon");
		} else {
			if (customer.getOccupation().equals(Occupation.developer.toString())) {
				customer.setCustomerGroup("developer");
			} else {
				customer.setCustomerGroup("chef");
			}
		}

		if (customer.getCustomerName().isEmpty()) {
			response.setErrorCode(CustomerConstants.ERROR_CODE_01);
			response.setErrorMessage(CustomerConstants.ERROR_LU01_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} else if (customer.getDob().isEmpty()) {
			response.setErrorCode(CustomerConstants.ERROR_CODE_07);
			response.setErrorMessage(CustomerConstants.ERROR_LU07_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} else if (customer.getEmail().isEmpty()) {
			response.setErrorCode(CustomerConstants.ERROR_CODE_08);
			response.setErrorMessage(CustomerConstants.ERROR_LU08_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} else if (custo != null) {
			response.setErrorCode(CustomerConstants.ERROR_CODE_03);
			response.setErrorMessage(CustomerConstants.ERROR_LU03_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} else if (customer.getOccupation().isEmpty()) {
			response.setErrorCode(CustomerConstants.ERROR_CODE_09);
			response.setErrorMessage(CustomerConstants.ERROR_LU09_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} else if (customer.getCustomerGroup().isEmpty()) {
			response.setErrorCode(CustomerConstants.ERROR_CODE_10);
			response.setErrorMessage(CustomerConstants.ERROR_LU10_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} else if (!customer.getOccupation().equals(Occupation.developer.toString())
				&& !customer.getOccupation().equals(Occupation.chef.toString())
				&& !customer.getOccupation().equals(Occupation.other.toString())
				&& !customer.getOccupation().equals(Occupation.plumber.toString())
				&& !customer.getOccupation().equals(Occupation.carpenter.toString())) {
			response.setErrorCode(CustomerConstants.ERROR_CODE_04);
			response.setErrorMessage(CustomerConstants.ERROR_LU04_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else if (!customer.getOccupation().equals(CustomerGroup.developer.toString())
				&& !customer.getOccupation().equals(CustomerGroup.chef.toString())
				&& !customer.getOccupation().equals(CustomerGroup.hikeon.toString())
				&& !customer.getOccupation().equals(CustomerGroup.NA.toString())) {
			response.setErrorCode(CustomerConstants.ERROR_CODE_05);
			response.setErrorMessage(CustomerConstants.ERROR_LU05_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else if (Validation.checkDobFormat(customer.getDob()) == false) {
			response.setErrorCode(CustomerConstants.ERROR_CODE_11);
			response.setErrorMessage(CustomerConstants.ERROR_LU11_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else if (Validation.calaculateAge(customer.getDob()) == false) {
			response.setErrorCode(CustomerConstants.ERROR_CODE_06);
			response.setErrorMessage(CustomerConstants.ERROR_LU06_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else {
			response = customerService.createCustomer(customer);
		}

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
