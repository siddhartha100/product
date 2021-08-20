package com.myretail.pricing.product.exception.handler;

import com.myretail.pricing.product.dto.InputValidationFailedResponse;
import com.myretail.pricing.product.exception.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler {

	@ExceptionHandler(InputValidationException.class)
	public ResponseEntity<InputValidationFailedResponse> handleInputValidationException(InputValidationException exception) {
		InputValidationFailedResponse response = new InputValidationFailedResponse();
		response.setInput(exception.getInput());
		response.setErrorCode(InputValidationException.ERROR_CODE);
		response.setMessage(exception.getMessage());
		return ResponseEntity.badRequest().body(response);
	}

}

