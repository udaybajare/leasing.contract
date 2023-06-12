package com.leasing.contract.service;

import java.util.List;
import java.util.Optional;

import com.leasing.contract.api.exception.ResourceNotFoundException;
import com.leasing.contract.api.model.CreateVehicle;
import com.leasing.contract.api.model.UpdateVehicle;
import com.leasing.contract.entity.Contract;
import com.leasing.contract.entity.Vehicle;
import com.leasing.contract.repository.ContractRepository;
import com.leasing.contract.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

	private final VehicleRepository vehicleRepository;
	private final ContractRepository contractRepository;

	@Autowired
	public VehicleServiceImpl(VehicleRepository vehicleRepository, ContractRepository contractRepository) {
		this.vehicleRepository = vehicleRepository;
		this.contractRepository = contractRepository;
	}

	@Override
	public Vehicle getVehicle(String vehicleId) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
		if (vehicle.isPresent()) {
			return vehicle.get();
		} else {
			throw new ResourceNotFoundException("Vehicle does not exist");
		}
	}

	@Override
	public String createVehicle(CreateVehicle createVehicle) {
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand(createVehicle.getBrand());
		vehicle.setModel(createVehicle.getModel());
		vehicle.setModelYear(createVehicle.getModelYear());
		vehicle.setPrice(createVehicle.getPrice());

		if(Strings.isNotBlank(createVehicle.getVin()))
			vehicle.setVin(createVehicle.getVin());

		vehicle = vehicleRepository.save(vehicle);
		return vehicle.getVehicleId();
	}

	@Override
	public String updateVehicle(String vehicleId, UpdateVehicle updateVehicle) {
		Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);

		if (vehicleOptional.isPresent()) {
			Vehicle vehicle = vehicleOptional.get();

			if (Strings.isNotBlank(updateVehicle.getBrand())) {
				vehicle.setBrand(updateVehicle.getBrand());
			}

			if (Strings.isNotBlank(updateVehicle.getModel())) {
				vehicle.setModel(updateVehicle.getModel());
			}

			if (0 != updateVehicle.getModelYear()) {
				vehicle.setModelYear(updateVehicle.getModelYear());
			}

			if (Strings.isNotBlank(updateVehicle.getVin())) {
				vehicle.setVin(updateVehicle.getVin());
			}

			if (0.0 != updateVehicle.getPrice()) {
				vehicle.setPrice(updateVehicle.getPrice());
			}

			if (null != updateVehicle.getContractNumber()) {
				Optional<Contract> contract = contractRepository.findById(updateVehicle.getContractNumber());
				if (contract.isPresent()) {
					vehicle.setContract(contract.get());
				} else {
					throw new ResourceNotFoundException("Contract does not exist");
				}
			}
			vehicle = vehicleRepository.save(vehicle);
			return vehicle.getVehicleId();
		} else {
			throw new ResourceNotFoundException("Vehicle does not exist");
		}
	}

	@Override
	public List<Vehicle> getAvailableVehicles() {
		return vehicleRepository.findByContractIsNull();
	}
}
