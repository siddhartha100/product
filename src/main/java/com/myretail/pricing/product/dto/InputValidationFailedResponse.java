package com.myretail.pricing.product.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InputValidationFailedResponse {
	private Integer errorCode;
	private Integer input;
	private String message;
}

