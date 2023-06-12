package com.leasing.contract.api;

import com.leasing.contract.api.model.CustomerRequest;
import com.leasing.contract.entity.Customer;
import com.leasing.contract.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable(required = true) String customerId) {
		return ResponseEntity.ok(customerService.getCustomer(customerId));
	}

	@PostMapping
	public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest customerRequest) {
		return ResponseEntity.ok(customerService.createCustomer(customerRequest));
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<String> updateCustomer(@PathVariable(required = true) String customerId,
			@RequestBody CustomerRequest customerRequest) {
		return ResponseEntity.ok(customerService.updateCustomer(customerId, customerRequest));
	}
}
