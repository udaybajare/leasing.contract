package com.leasing.contract.repository;

import java.util.List;

import com.leasing.contract.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
	List<Vehicle> findByContractIsNull();
}
