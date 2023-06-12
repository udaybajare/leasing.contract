package com.leasing.contract.utils;

import com.leasing.contract.entity.Customer;
import com.leasing.contract.entity.Vehicle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final String USER_ID_PREFIX = "USR";
	private static final String CONTRACT_ID_PREFIX = "LCT";
	private static final String VEHICLE_ID_PREFIX = "VHL";

	public static String generateUserId() {
		return USER_ID_PREFIX + randomAlphaNumeric(12);
	}

	public static String generateContractId() {
		return CONTRACT_ID_PREFIX + randomAlphaNumeric(12);
	}

	public static String generateVehicleId() {
		return VEHICLE_ID_PREFIX + randomAlphaNumeric(12);
	}

	private static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public String getCustomerFullName(Customer customer){
		return new StringBuilder()
				.append(customer.getFirstName())
				.append(StringUtils.SPACE)
				.append(customer.getLastName())
				.toString();
	}

	public String getVehicleDetail(Vehicle vehicle){
		return new StringBuilder()
				.append(vehicle.getBrand())
				.append(StringUtils.SPACE)
				.append(vehicle.getModel())
				.append(StringUtils.SPACE)
				.append("(")
				.append(vehicle.getModelYear())
				.append(")")
				.toString();
	}
}
