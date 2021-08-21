package com.myretail.pricing.product.dto.pdp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class ProductTest {

	@Mock
	private AvailableToPromiseNetwork availableToPromiseNetwork;
	@Mock
	private Item item;
	@InjectMocks
	private Product product;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testProduct() {
		assertNotNull(product);
		assertNotNull(product.getItem());
		assertNotNull(product.getAvailableToPromiseNetwork());
	}

	@Test
	public void testProductNoArgumentConstructor() {
		Product product = new Product();
		product.setItem(item);
		product.setAvailableToPromiseNetwork(availableToPromiseNetwork);
		assertNotNull(product);
		assertNotNull(product.getItem());
		assertNotNull(product.getAvailableToPromiseNetwork());
	}

}
