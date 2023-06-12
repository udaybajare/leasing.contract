package com.leasing.contract.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.leasing.contract.api.exception.ResourceNotFoundException;
import com.leasing.contract.api.model.ContractDetails;
import com.leasing.contract.api.model.ContractRequest;
import com.leasing.contract.api.model.UpdateVehicle;
import com.leasing.contract.entity.Contract;
import com.leasing.contract.entity.Customer;
import com.leasing.contract.entity.Vehicle;
import com.leasing.contract.repository.ContractRepository;
import com.leasing.contract.utils.CommonUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

	private final ContractRepository contractRepository;
	private final CustomerService customerService;
	private final VehicleService vehicleService;
	private final CommonUtils commonUtils;

	@Autowired
	public ContractServiceImpl(ContractRepository contractRepository, CustomerService customerService,
			VehicleService vehicleService, CommonUtils commonUtils) {
		this.contractRepository = contractRepository;
		this.customerService = customerService;
		this.vehicleService = vehicleService;
		this.commonUtils = commonUtils;
	}

	@Override
	public List<ContractDetails> getAllContracts() {
		List<Contract> contracts = contractRepository.findAll();
		return mapToContractDetails(contracts);
	}

	private ContractDetails mapToContractDetails(Contract contract) {
		List<ContractDetails> contractDetailsList = mapToContractDetails(Collections.singletonList(contract));
		if (contractDetailsList.size() > 0) {
			return contractDetailsList.get(0);
		} else {
			throw new ResourceNotFoundException("Exception getting contract details");
		}
	}

	private List<ContractDetails> mapToContractDetails(List<Contract> contracts) {
		List<ContractDetails> contractDetailsList = new ArrayList<>();

		for (Contract contract : contracts) {

			Vehicle vehicle = contract.getVehicle();

			contractDetailsList.add(
					new ContractDetails(
							contract.getContractNumber(),
							commonUtils.getCustomerFullName(contract.getCustomer()),
							commonUtils.getVehicleDetail(contract.getVehicle()),
							Strings.isEmpty(vehicle.getVin()) ? "-" : vehicle.getVin(),
							contract.getMonthlyRate(),
							vehicle.getPrice()));
		}

		return contractDetailsList;
	}

	@Override
	public ContractDetails getContractDetails(String contractNumber) {

		Optional<Contract> contract = contractRepository.findById(contractNumber);
		ContractDetails contractDetails;

		if (contract.isPresent()) {
			contractDetails = mapToContractDetails(contract.get());
		} else {
			throw new ResourceNotFoundException("Contract does not exist");
		}
		return contractDetails;
	}

	@Override
	public String createContract(ContractRequest contractRequest) {

		Customer customer = customerService.getCustomer(contractRequest.getCustomerId());
		Vehicle vehicle = vehicleService.getVehicle(contractRequest.getVehicleId());

		Contract newContract = new Contract();
		newContract.setCustomer(customer);
		newContract.setVehicle(vehicle);
		newContract.setMonthlyRate(contractRequest.getMonthlyRate());

		Contract contract = contractRepository.save(newContract);


		vehicleService.updateVehicle(vehicle.getVehicleId(),
				UpdateVehicle
						.builder()
						.contractNumber(contract.getContractNumber())
						.build());

		return contract.getContractNumber();
	}
}
