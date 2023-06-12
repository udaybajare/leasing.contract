package com.leasing.contract.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class CustomerRequest {
	private String firstName;
	private String lastName;

	@JsonFormat(pattern="dd.MM.yyyy")
	private Date dateOfBirth;
}
