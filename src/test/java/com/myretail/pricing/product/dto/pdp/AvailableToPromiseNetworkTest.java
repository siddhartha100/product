package com.myretail.pricing.product.dto.pdp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvailableToPromiseNetworkTest {

	private AvailableToPromiseNetwork availableToPromiseNetwork1;
	private AvailableToPromiseNetwork availableToPromiseNetwork2;

	@Before
	public void init() {
		availableToPromiseNetwork1 = new AvailableToPromiseNetwork("123");
		availableToPromiseNetwork2 = new AvailableToPromiseNetwork();
		availableToPromiseNetwork2.setProductId("123");
	}

	@Test
	public void testAvailableToPromiseNetwork() {
		assertNotNull(availableToPromiseNetwork1);
		assertNotNull(availableToPromiseNetwork2);
		assertEquals(availableToPromiseNetwork1.getProductId(), "123");
		assertEquals(availableToPromiseNetwork2.getProductId(), "123");
	}

}
