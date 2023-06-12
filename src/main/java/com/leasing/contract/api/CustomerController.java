package com.leasing.contract.api;

import com.leasing.contract.api.model.CustomerRequest;
import com.leasing.contract.entity.Customer;
import com.leasing.contract.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@Operation(summary = "Get Customer details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer Details"),
			@ApiResponse(responseCode = "404", description = "Resource not found")
	})
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable(required = true) String customerId) {
		return ResponseEntity.ok(customerService.getCustomer(customerId));
	}

	@Operation(summary = "Create Customer details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer Details"),
			@ApiResponse(responseCode = "500", description = "Server error")
	})
	@PostMapping
	public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest customerRequest) {
		return ResponseEntity.ok(customerService.createCustomer(customerRequest));
	}

	@Operation(summary = "Update Customer details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer Details"),
			@ApiResponse(responseCode = "500", description = "Server error"),
			@ApiResponse(responseCode = "404", description = "Resource not found")
	})
	@PutMapping("/{customerId}")
	public ResponseEntity<String> updateCustomer(@PathVariable(required = true) String customerId,
			@RequestBody CustomerRequest customerRequest) {
		return ResponseEntity.ok(customerService.updateCustomer(customerId, customerRequest));
	}
}
