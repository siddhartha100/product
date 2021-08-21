package com.myretail.pricing.product.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CurrentPriceTest {

	private CurrentPrice currentPrice;

	@Before
	public void init() {
		currentPrice = new CurrentPrice();
		currentPrice.setCurrencyCode("USD");
		currentPrice.setValue(10.10);
	}

	@Test
	public void testCurrentPrice() {
		assertNotNull(currentPrice);
		assertEquals(currentPrice.getCurrencyCode(), "USD");
		assertEquals(currentPrice.getValue(), Double.valueOf(10.10));
	}

	@Test
	public void testCurrentPriceAllArgumentConstructor() {
		CurrentPrice currentPrice = new CurrentPrice(10.10, "USD");
		assertNotNull(currentPrice);
		assertEquals(currentPrice.getCurrencyCode(), "USD");
		assertEquals(currentPrice.getValue(), Double.valueOf(10.10));
	}

}
