package com.myretail.pricing.product.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InputValidationFailedResponseTest {

	private InputValidationFailedResponse inputValidationFailedResponse;

	@Before
	public void init(){
		inputValidationFailedResponse = new InputValidationFailedResponse();
		inputValidationFailedResponse.setInput(-1);
		inputValidationFailedResponse.setMessage("ERROR");
		inputValidationFailedResponse.setErrorCode(1000);
	}

	@Test
	public void testInputValidationFailedResponse(){
		assertNotNull(inputValidationFailedResponse);
		assertEquals(inputValidationFailedResponse.getInput(), Integer.valueOf(-1));
		assertEquals(inputValidationFailedResponse.getMessage(),"ERROR");
		assertEquals(inputValidationFailedResponse.getErrorCode(), Integer.valueOf(1000));
		assertNotNull(inputValidationFailedResponse.toString());
	}

}
