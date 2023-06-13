package com.leasing.contract.api;

import java.util.List;

import com.leasing.contract.api.model.VehicleRequest;
import com.leasing.contract.api.model.UpdateVehicle;
import com.leasing.contract.entity.Vehicle;
import com.leasing.contract.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@Operation(summary = "Get Vehicle details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Vehicle Details"),
			@ApiResponse(responseCode = "500", description = "Server error"),
			@ApiResponse(responseCode = "404", description = "Resource not found")
	})
	@GetMapping("/{vehicleId}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable(required = true) String vehicleId){
		return ResponseEntity.ok(vehicleService.getVehicle(vehicleId));
	}

	@Operation(summary = "Get Available Vehicles")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Vehicle Details"),
			@ApiResponse(responseCode = "500", description = "Server error")
	})
	@GetMapping
	public ResponseEntity<List<Vehicle>> getAvailableVehicles(){
		return ResponseEntity.ok(vehicleService.getAvailableVehicles());
	}

	@Operation(summary = "Create new Vehicles")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Vehicle Details"),
			@ApiResponse(responseCode = "500", description = "Server error")
	})
	@PostMapping
	private ResponseEntity<String> createVehicle(@RequestBody VehicleRequest vehicleRequest){
		return ResponseEntity.ok(vehicleService.createVehicle(vehicleRequest));
	}

	@Operation(summary = "Update existing Vehicles")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Vehicle Details"),
			@ApiResponse(responseCode = "500", description = "Server error"),
			@ApiResponse(responseCode = "404", description = "Resource not found")
	})
	@PutMapping("/{vehicleId}")
	private ResponseEntity<String> updateVehicle(@PathVariable(required = true) String vehicleId,
			@RequestBody UpdateVehicle createVehicle){
		return ResponseEntity.ok(vehicleService.updateVehicle(vehicleId, createVehicle));
	}
}
