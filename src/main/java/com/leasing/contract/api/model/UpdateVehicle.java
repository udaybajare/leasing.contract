package com.leasing.contract.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateVehicle {
	private String contractNumber;
	private String brand;
	private String model;
	private int modelYear;
	private String vin;
	private double price;
}
