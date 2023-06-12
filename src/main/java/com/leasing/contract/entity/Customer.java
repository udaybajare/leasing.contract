package com.leasing.contract.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.leasing.contract.utils.CommonUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "customer_id")
	private String customerId = CommonUtils.generateUserId();

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@DateTimeFormat(pattern="dd.MM.yyyy")
	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "contract_number")
	private Set<Contract> contract;
}
