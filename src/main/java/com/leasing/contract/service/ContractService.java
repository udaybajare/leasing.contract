package com.leasing.contract.service;

import java.util.List;

import com.leasing.contract.api.model.ContractDetails;
import com.leasing.contract.api.model.ContractRequest;

public interface ContractService {

	List<ContractDetails> getAllContracts();

	ContractDetails getContractDetails(String contractNumber);

	String createContract(ContractRequest contractRequest);
}
