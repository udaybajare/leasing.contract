package com.leasing.contract.service;

import java.util.Date;

import com.leasing.contract.api.model.CustomerRequest;
import com.leasing.contract.entity.Customer;
import com.leasing.contract.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Captor
	private ArgumentCaptor<Customer> customerArgumentCaptor;

	@Test
	public void whenCreateCustomer_ShouldReturnCustomer(){

		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.setFirstName("Jane");
		customerRequest.setLastName("Doe");
		customerRequest.setDateOfBirth(new Date());

		Customer customer = new Customer();
		customer.setFirstName("Jane");
		customer.setLastName("Doe");
		customer.setDateOfBirth(new Date());

		when(customerRepository.save(customerArgumentCaptor.capture())).thenReturn(customer);

		customerService.createCustomer(customerRequest);

		assert(customerArgumentCaptor.getValue().getFirstName()).equalsIgnoreCase(customerRequest.getFirstName());
		assert(customerArgumentCaptor.getValue().getLastName()).equalsIgnoreCase(customerRequest.getLastName());
		assert(customerArgumentCaptor.getValue().getDateOfBirth()).equals(customerRequest.getDateOfBirth());

		verify(customerRepository).save(customerArgumentCaptor.getValue());
	}
}
