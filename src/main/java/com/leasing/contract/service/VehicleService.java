package com.leasing.contract.service;

import java.util.List;

import com.leasing.contract.api.model.CreateVehicle;
import com.leasing.contract.api.model.UpdateVehicle;
import com.leasing.contract.entity.Vehicle;

public interface VehicleService {
	Vehicle getVehicle(String vehicleId);

	String createVehicle(CreateVehicle createVehicle);

	String updateVehicle(String vehicleId, UpdateVehicle createVehicle);

	List<Vehicle> getAvailableVehicles();
}
