package com.myretail.pricing.product.repository;

import com.myretail.pricing.product.dto.CurrentPrice;
import com.myretail.pricing.product.dto.ProductResponse;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testRepository() {
		ProductResponse dto = getMockProductResponse();
		ProductResponse response = productRepository.save(dto);
		assertNotNull(response);
		assertEquals(response.get_id(),Integer.valueOf(1));
	}

	private ProductResponse getMockProductResponse() {
		ProductResponse response = new ProductResponse();
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setCurrencyCode("USD");
		currentPrice.setValue(10.01);
		response.setCurrentPrice(currentPrice);
		response.set_id(1);
		response.setName("Computer");
		return response;
	}

}
