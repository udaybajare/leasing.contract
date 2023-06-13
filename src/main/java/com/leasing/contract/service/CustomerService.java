package com.leasing.contract.service;

import java.util.List;

import com.leasing.contract.api.model.CustomerRequest;
import com.leasing.contract.entity.Customer;

public interface CustomerService {
	Customer getCustomer(String customerId);

	String createCustomer(CustomerRequest customerRequest);

	String updateCustomer(String customerId, CustomerRequest customerRequest);

	List<Customer> getAllCustomers();
}
