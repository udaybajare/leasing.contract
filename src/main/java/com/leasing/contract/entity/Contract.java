package com.leasing.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.leasing.contract.utils.CommonUtils;
import lombok.Data;

@Data
@Entity
@Table(name = "contract")
public class Contract {

	@Id
	@Column(name="contract_number")
	private String contractNumber = CommonUtils.generateContractId();

	@Column(name="monthly_rate")
	private double monthlyRate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;
}
