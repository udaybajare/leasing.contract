package com.leasing.contract.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class VehicleRequest {
	@NotBlank(message = "Brand is required")
	private String brand;
	@NotBlank(message = "Model is required")
	private String model;
	@NotBlank(message = "Model year is required")
	private int modelYear;
	private String vin;
	@NotBlank(message = "Price is required")
	private double price;
}
