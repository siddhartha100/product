package com.myretail.pricing.product.service;

import com.myretail.pricing.product.configuration.ProductApiConfiguration;
import com.myretail.pricing.product.dto.CurrentPrice;
import com.myretail.pricing.product.dto.ProductResponse;
import com.myretail.pricing.product.dto.pdp.*;
import com.myretail.pricing.product.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Mock
	RestTemplate restTemplate;
	@Mock
	ProductApiConfiguration apiConfiguration;
	@Mock
	ProductRepository productRepository;
	@InjectMocks
	ProductService productService;

	@Test
	public void testUpdateProductInformation() {
		ProductResponse response = getMockProductResponse().isPresent() ? getMockProductResponse().get() : new ProductResponse();
		when(productRepository.save(any())).thenReturn(response);
		ProductResponse pr = productService.updateProductInformation(response);
		assertEquals(pr.get_id(), response.get_id());
	}

	@Test
	public void testProductService() throws ExecutionException, InterruptedException {
		Optional<ProductResponse> response = getMockProductResponse();
		when(apiConfiguration.getBaseurl()).thenReturn("base");
		when(apiConfiguration.getExcludes()).thenReturn("exclude");
		when(apiConfiguration.getPath()).thenReturn("path");
		ResponseEntity<TcinResponse> generateMockResponse = generateMockApiResponse();
		when(restTemplate.exchange(
				ArgumentMatchers.anyString(),
				ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(),
				ArgumentMatchers.<Class<TcinResponse>>any())).thenReturn(generateMockResponse);
		when(productRepository.findById(anyInt())).thenReturn(response);
		ProductResponse pr = productService.searchProductById(1);
		assertNotNull(pr);
		assertEquals(pr.get_id(), Integer.valueOf(1));
		assertEquals(pr.getCurrentPrice().getValue(), Double.valueOf(10.01));
	}

	private Optional<ProductResponse> getMockProductResponse() {
		ProductResponse productResponse = new ProductResponse();
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setValue(10.01);
		currentPrice.setCurrencyCode("USD");
		productResponse.setCurrentPrice(currentPrice);
		productResponse.setName("testItem");
		productResponse.set_id(1);
		return Optional.of(productResponse);
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		return headers;
	}

	// Get Mock ResponseEntity
	private ResponseEntity<TcinResponse> generateMockApiResponse() {
		TcinResponse response = new TcinResponse();
		Product product = new Product();
		AvailableToPromiseNetwork availableToPromiseNetwork = new AvailableToPromiseNetwork("1");
		Item item = new Item();
		ProductDescription productDescription = new ProductDescription("Computer");
		item.setProductDescription(productDescription);
		product.setItem(item);
		product.setAvailableToPromiseNetwork(availableToPromiseNetwork);
		response.setProduct(product);

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<TcinResponse>(response, header, HttpStatus.OK);
	}

}
