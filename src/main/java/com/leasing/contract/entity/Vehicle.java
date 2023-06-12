package com.leasing.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.leasing.contract.utils.CommonUtils;
import lombok.Data;

@Data
@Entity
public class Vehicle {

	@Id
	@Column(name = "vehicle_id")
	private String vehicleId = CommonUtils.generateVehicleId();

	@Column(name = "brand")
	private String brand;

	@Column(name = "model")
	private String model;

	@Column(name = "model_year")
	private int modelYear; // Change the type

	@Column(name = "vin")
	private String vin; // Change the type

	@Column(name = "price")
	private double price;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contract_number")
	private Contract contract;
}
