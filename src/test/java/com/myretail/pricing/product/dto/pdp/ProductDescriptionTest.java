package com.myretail.pricing.product.dto.pdp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductDescriptionTest {

	private ProductDescription productDescription1;
	private ProductDescription productDescription2;

	@Before
	public void init() {
		productDescription1 = new ProductDescription("Computer");
		productDescription2 = new ProductDescription();
		productDescription2.setTitle("Computer");
	}

	@Test
	public void testProductDescriptionAllArgumentConstrcutor() {
		assertNotNull(productDescription1);
		assertEquals(productDescription1.getTitle(), "Computer");
	}

	@Test
	public void testProductDescriptionNoArgumentConstrcutor() {
		assertNotNull(productDescription2);
		assertEquals(productDescription2.getTitle(), "Computer");
	}

}
