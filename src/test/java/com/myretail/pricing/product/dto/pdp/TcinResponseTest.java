package com.myretail.pricing.product.dto.pdp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class TcinResponseTest {

	@Mock
	private Product product;
	private TcinResponse tcinResponse;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		tcinResponse = new TcinResponse();
		tcinResponse.setProduct(product);
	}

	@Test
	public void testTcinResponseNoArgumentConstructor() {
		assertNotNull(tcinResponse);
		assertNotNull(tcinResponse.getProduct());
	}

	@Test
	public void testTcinResponseAllArgumentConstructor() {
		TcinResponse tcinResponse = new TcinResponse(product);
		assertNotNull(tcinResponse);
		assertNotNull(tcinResponse.getProduct());
	}

}
