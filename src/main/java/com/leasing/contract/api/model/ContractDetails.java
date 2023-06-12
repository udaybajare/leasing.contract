package com.leasing.contract.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDetails {

	private String contractNumber;
	private String CustomerName;
	private String vehicle;
	private String vin;
	private double monthlyRate;
	private double vehiclePrice;
}
