package com.myretail.pricing.product.dto.pdp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class ItemTest {

	@Mock
	private ProductDescription productDescription;
	private Item item1;
	private Item item2;


	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		item1 = new Item(productDescription);
		item2 = new Item();
		item2.setProductDescription(productDescription);
	}

	@Test
	public void testItem() {
		assertNotNull(item1);
		assertNotNull(item2);
		assertNotNull(item1.getProductDescription());
		assertNotNull(item2.getProductDescription());
	}

}
