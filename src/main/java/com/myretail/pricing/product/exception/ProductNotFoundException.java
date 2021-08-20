package com.myretail.pricing.product.exception;

public class ProductNotFoundException extends RuntimeException {

	public static final Integer ERROR_CODE = 1001;
	public static String MESSAGE = "Product Not Found.";
	private Integer input;

	public ProductNotFoundException(Integer input) {
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
