package com.leasing.contract.api;

import java.util.List;

import com.leasing.contract.api.model.ContractDetails;
import com.leasing.contract.api.model.ContractRequest;
import com.leasing.contract.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contract")
public class ContractController {

	private final ContractService contractService;

	@Autowired
	public ContractController(ContractService contractService) {
		this.contractService = contractService;
	}

	@Operation(summary = "Get all Contract details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all contract details"),
			@ApiResponse(responseCode = "500", description = "Server Error")
	})
	@GetMapping
	public ResponseEntity<List<ContractDetails>> getAllContracts(){
		//TODO : Implement JPA pagination
		return ResponseEntity.ok(contractService.getAllContracts());
	}

	@Operation(summary = "Creates a new contract")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Created contract"),
			@ApiResponse(responseCode = "404", description = "Vehicle or Customer not found"),
			@ApiResponse(responseCode = "500", description = "Server Error")})
	@PostMapping
	public ResponseEntity<String> createContract(@RequestBody ContractRequest contractRequest){
		return ResponseEntity.ok(contractService.createContract(contractRequest));
	}

	@Operation(summary = "Get Contract details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Individual contract details"),
			@ApiResponse(responseCode = "404", description = "Contract not found"),
			@ApiResponse(responseCode = "500", description = "Server Error")
	})
	@GetMapping("/{contractNumber}")
	public ResponseEntity<ContractDetails> getContractDetails(@PathVariable("contractNumber") String contractNumber){
		return ResponseEntity.ok(contractService.getContractDetails(contractNumber));
	}
}
