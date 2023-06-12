package com.leasing.contract.api.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
	private String message;
}
