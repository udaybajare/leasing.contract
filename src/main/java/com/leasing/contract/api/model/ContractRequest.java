package com.leasing.contract.api.model;

import lombok.Data;

@Data
public class ContractRequest {

	private String customerId;
	private String vehicleId;
	private double monthlyRate;
}
