package com.leasing.contract.api;

import java.util.List;

import com.leasing.contract.api.model.CreateVehicle;
import com.leasing.contract.api.model.UpdateVehicle;
import com.leasing.contract.entity.Vehicle;
import com.leasing.contract.service.VehicleService;
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
@RequestMapping("/vehicle")
public class VehicleController {

	private final VehicleService vehicleService;

	@Autowired
	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@GetMapping("/{vehicleId}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable(required = true) String vehicleId){
		return ResponseEntity.ok(vehicleService.getVehicle(vehicleId));
	}

	@GetMapping
	public ResponseEntity<List<Vehicle>> getAvailableVehicles(){
		return ResponseEntity.ok(vehicleService.getAvailableVehicles());
	}

	@PostMapping
	private ResponseEntity<String> createVehicle(@RequestBody CreateVehicle createVehicle){
		return ResponseEntity.ok(vehicleService.createVehicle(createVehicle));
	}

	@PutMapping("/{vehicleId}")
	private ResponseEntity<String> createVehicle(@PathVariable(required = true) String vehicleId,
			@RequestBody UpdateVehicle createVehicle){
		return ResponseEntity.ok(vehicleService.updateVehicle(vehicleId, createVehicle));
	}
}
