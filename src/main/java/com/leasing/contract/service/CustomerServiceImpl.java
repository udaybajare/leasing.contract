package com.leasing.contract.service;

import java.util.List;
import java.util.Optional;

import com.leasing.contract.api.exception.ResourceNotFoundException;
import com.leasing.contract.api.model.CustomerRequest;
import com.leasing.contract.entity.Customer;
import com.leasing.contract.repository.CustomerRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer getCustomer(String customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isPresent()) {
			return customer.get();
		} else {
			throw new ResourceNotFoundException("Customer does not exist");
		}
	}

	@Override
	public String createCustomer(CustomerRequest customerRequest) {

		Customer customer = new Customer();
		customer.setFirstName(customerRequest.getFirstName());
		customer.setLastName(customerRequest.getLastName());
		customer.setDateOfBirth(customerRequest.getDateOfBirth());

		return customerRepository.save(customer).getCustomerId();
	}

	@Override
	public String updateCustomer(String customerId, CustomerRequest customerRequest) {

		Optional<Customer> customerOptional = customerRepository.findById(customerId);

		if (customerOptional.isPresent()) {
			Customer customer = customerOptional.get();

			if (Strings.isNotBlank(customerRequest.getFirstName())) {
				customer.setFirstName(customerRequest.getFirstName());
			}
			if (Strings.isNotBlank(customerRequest.getLastName())) {
				customer.setLastName(customerRequest.getLastName());
			}
			if (null != customerRequest.getDateOfBirth()) {
				customer.setDateOfBirth(customerRequest.getDateOfBirth());
			}
			customerRepository.save(customer);
		} else {
			throw new ResourceNotFoundException("Customer does not exist");
		}

		return customerId;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
}
