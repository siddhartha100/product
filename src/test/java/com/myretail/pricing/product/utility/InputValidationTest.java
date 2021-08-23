package com.myretail.pricing.product.utility;


import com.myretail.pricing.product.dto.ProductResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InputValidationTest {

    private InputValidation inputValidation;
    private ProductResponse response;

    @Before
    public void init() {
        inputValidation = new InputValidation();
        response = generateMockProductResponse();
    }

    @Test
    public void testRequestValidationPass() {
        assertTrue(inputValidation.requestValidation(1, response));
    }

    @Test
    public void testRequestValidationFail() {
        assertFalse(inputValidation.requestValidation(2, response));
    }

    private ProductResponse generateMockProductResponse() {
        ProductResponse response = new ProductResponse();
        response.set_id(1);
        response.setName("Computer");
        return response;
    }

}
