package com.leasing.contract.service;

import com.leasing.contract.api.model.VehicleRequest;
import com.leasing.contract.entity.Vehicle;
import com.leasing.contract.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

	@Mock
	private VehicleRepository vehicleRepository;

	@InjectMocks
	private VehicleServiceImpl vehicleService;

	@Captor
	private ArgumentCaptor<Vehicle> vehicleArgumentCaptor;

	@Test
	public void whenCreateCustomer_ShouldReturnCustomer() {

		VehicleRequest vehicleRequest = new VehicleRequest();
		vehicleRequest.setBrand("BMW");
		vehicleRequest.setModel("X1");
		vehicleRequest.setModelYear(2019);
		vehicleRequest.setPrice(35000);

		Vehicle vehicle = new Vehicle();
		vehicle.setBrand("BMW");
		vehicle.setModel("X1");
		vehicle.setModelYear(2019);
		vehicle.setPrice(35000);

		when(vehicleRepository.save(vehicleArgumentCaptor.capture())).thenReturn(vehicle);

		vehicleService.createVehicle(vehicleRequest);

		assert (vehicleArgumentCaptor.getValue().getBrand()).equalsIgnoreCase(vehicleRequest.getBrand());
		assert (vehicleArgumentCaptor.getValue().getModel()).equalsIgnoreCase(vehicleRequest.getModel());
		assert (vehicleArgumentCaptor.getValue().getModelYear() == vehicleRequest.getModelYear());
		assert (vehicleArgumentCaptor.getValue().getPrice() == vehicleRequest.getPrice());

		verify(vehicleRepository).save(vehicleArgumentCaptor.getValue());
	}
}
