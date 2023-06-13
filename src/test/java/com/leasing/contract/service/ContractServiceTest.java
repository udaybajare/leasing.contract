package com.leasing.contract.service;

import com.leasing.contract.api.model.ContractRequest;
import com.leasing.contract.api.model.UpdateVehicle;
import com.leasing.contract.entity.Contract;
import com.leasing.contract.entity.Customer;
import com.leasing.contract.entity.Vehicle;
import com.leasing.contract.repository.ContractRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContractServiceTest {

	@Mock
	private ContractRepository contractRepository;

	@Mock
	private CustomerServiceImpl customerService;

	@Mock
	private VehicleServiceImpl vehicleService;

	@InjectMocks
	private ContractServiceImpl contractService;

	@Captor
	private ArgumentCaptor<Contract> contractArgumentCaptor;

	@Test
	public void whenCreateContract_ShouldReturnContract(){

		Customer customer = new Customer();
		Vehicle vehicle = new Vehicle();

		Contract contract = new Contract();
		contract.setMonthlyRate(350);
		contract.setCustomer(customer);
		contract.setVehicle(vehicle);

		ContractRequest contractRequest = new ContractRequest();
		contractRequest.setMonthlyRate(350);
		contractRequest.setCustomerId(customer.getCustomerId());
		contractRequest.setVehicleId(customer.getCustomerId());

		when(customerService.getCustomer(ArgumentMatchers.anyString())).thenReturn(customer);
		when(vehicleService.getVehicle(ArgumentMatchers.anyString())).thenReturn(vehicle);
		when(vehicleService.updateVehicle(ArgumentMatchers.anyString(),
				ArgumentMatchers.any(UpdateVehicle.class))).thenReturn("");

		when(contractRepository.save(contractArgumentCaptor.capture())).thenReturn(contract);

		String contractId = contractService.createContract(contractRequest);

		assert(contractArgumentCaptor.getValue().getMonthlyRate() == contract.getMonthlyRate());
		assert(contractArgumentCaptor.getValue().getCustomer().equals(contract.getCustomer()));
		assert(contractArgumentCaptor.getValue().getVehicle().equals(contract.getVehicle()));

		verify(contractRepository).save(contractArgumentCaptor.getValue());
	}
}
