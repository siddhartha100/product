package com.myretail.pricing.product.dto;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class ProductResponseTest {

	private ProductResponse productResponse;
	@Mock
	private CurrentPrice currentPrice;


	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		productResponse = new ProductResponse();
		productResponse.set_id(1);
		productResponse.setName("Computer");
		productResponse.setCurrentPrice(currentPrice);
	}

	@Test
	public void testProductResponse() {
		when(currentPrice.getCurrencyCode()).thenReturn("USD");
		when(currentPrice.getValue()).thenReturn(10.01);
		assertNotNull(productResponse);
		assertNotNull(productResponse.getCurrentPrice());
		assertEquals(productResponse.get_id(), Integer.valueOf(1));
		assertEquals(productResponse.getName(), "Computer");
		assertEquals(productResponse.getCurrentPrice().getValue(), Double.valueOf(10.01));
		assertEquals(productResponse.getCurrentPrice().getCurrencyCode(), "USD");
	}

	@Test
	public void testProductResponseAllArgumentConstructor() {
		ProductResponse productResponse = new ProductResponse(1, "Computer", currentPrice);
		when(currentPrice.getCurrencyCode()).thenReturn("USD");
		when(currentPrice.getValue()).thenReturn(10.01);
		assertNotNull(productResponse);
		assertNotNull(productResponse.getCurrentPrice());
		assertEquals(productResponse.get_id(), Integer.valueOf(1));
		assertEquals(productResponse.getName(), "Computer");
		assertEquals(productResponse.getCurrentPrice().getValue(), Double.valueOf(10.01));
		assertEquals(productResponse.getCurrentPrice().getCurrencyCode(), "USD");
	}

	@Test
	public void testProductResponseToString() {
		ProductResponse productResponse = new ProductResponse(1, "Computer", currentPrice);
		when(currentPrice.getCurrencyCode()).thenReturn("USD");
		when(currentPrice.getValue()).thenReturn(10.01);
		assertNotNull(productResponse.toString());
	}

}

