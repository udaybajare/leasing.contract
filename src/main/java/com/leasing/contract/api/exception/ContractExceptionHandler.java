package com.leasing.contract.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class can further be updated to create various exception handlers.
 */
@ControllerAdvice
@Slf4j
public class ContractExceptionHandler {

	/**
	 * To handle Customer or Vehicle not found exception during contract creation
	 */
	@ExceptionHandler(value=ResourceNotFoundException.class)
	public ResponseEntity<String> notFoundException(RuntimeException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * To handle other general exception scenarios
	 */
	@ExceptionHandler(value={Exception.class, RuntimeException.class})
	public ResponseEntity<String> generalException(Exception exception){
		log.error(exception.getMessage());
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
	}
}
