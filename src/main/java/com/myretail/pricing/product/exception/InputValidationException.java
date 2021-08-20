package com.myretail.pricing.product.exception;

public class InputValidationException extends RuntimeException {

	public static final Integer ERROR_CODE = 1000;
	public static String MESSAGE = "Input validation failed.";
	private Integer input;

	public InputValidationException(Integer input) {
		super(MESSAGE);
		this.input = input;
	}

	public Integer getInput() {
		return input;
	}

	public void setInput(Integer input) {
		this.input = input;
	}

}
