package com.myretail.pricing.product.service;

import com.myretail.pricing.product.configuration.ProductApiConfiguration;
import com.myretail.pricing.product.dto.CurrentPrice;
import com.myretail.pricing.product.dto.ProductResponse;
import com.myretail.pricing.product.dto.pdp.TcinResponse;
import com.myretail.pricing.product.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

	private static final Logger LOGGER = LogManager.getLogger(ProductService.class);
	private final RestTemplate restTemplate;
	private final ProductApiConfiguration apiConfiguration;
	private final ProductRepository productRepository;

	// Constructor injection
	public ProductService(RestTemplate restTemplate, ProductApiConfiguration apiConfiguration, ProductRepository productRepository) {
		this.restTemplate = restTemplate;
		this.apiConfiguration = apiConfiguration;
		this.productRepository = productRepository;
	}

	// Search product information by product id
	public ProductResponse searchProductById(Integer productId) throws ExecutionException, InterruptedException {

		ProductResponse response = new ProductResponse();

		// Fetch product information asynchronously from external api
		CompletableFuture<TcinResponse> productAllAttributes = CompletableFuture.supplyAsync(
				() -> getProductInformation(productId, this.apiConfiguration, this.restTemplate)
		);

		// Fetch cost information asynchronously from mongo db
		CompletableFuture<Optional<ProductResponse>> productCostAttributes = CompletableFuture.supplyAsync(
				() -> searchProductCost(productId, this.productRepository)
		);

		//  Wait till both types of data is available since we need both of them in the api response
		CompletableFuture<Void> wrapper = CompletableFuture.allOf(productAllAttributes, productCostAttributes);
		wrapper.get();

		// Create response object and return
		createProductResponse(response, productCostAttributes.get(), productAllAttributes.get());

		return response;

	}

	// Update product information to database, product id is the key
	public ProductResponse updateProductCost(ProductResponse productResponse) {
		return this.productRepository.save(productResponse);
	}

	// Create a response object for product search api
	private void createProductResponse(ProductResponse response, Optional<ProductResponse> productCostAttributes, TcinResponse productAllAttributes) {

		//update other product attributes
		if (productAllAttributes != null && productAllAttributes.getProduct() != null) {
			response.set_id(Integer.parseInt(productAllAttributes.getProduct().getAvailableToPromiseNetwork().getProductId()));
			response.setName(productAllAttributes.getProduct().getItem().getProductDescription().getTitle());
		} else {
			// No need to process since key is not available
			return;
		}

		// update price information
		CurrentPrice currentPrice = new CurrentPrice();
		if (productCostAttributes.isPresent()) {
			currentPrice.setCurrencyCode(productCostAttributes.get().getCurrentPrice().getCurrencyCode());
			currentPrice.setValue(productCostAttributes.get().getCurrentPrice().getValue());
			response.setCurrentPrice(currentPrice);
		}

	}

	// Calling the external api to find product information
	private TcinResponse getProductInformation(Integer productId, ProductApiConfiguration apiConfiguration, RestTemplate restTemplate) {

		ResponseEntity<TcinResponse> response = null;
		TcinResponse data = new TcinResponse();

		String url = getUrl(apiConfiguration, productId);
		HttpEntity<String> requestEntity = new HttpEntity<>(null, getHeaders());

		// Call the service
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, TcinResponse.class);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			LOGGER.info("Exception occurred during product search of product id {}. Error code: {}", productId, e.getStatusCode());
		}

		// Get the response if response is available
		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			data = response.getBody();
			return data;
		}
		return data;
	}

	// Search database for a product
	private Optional<ProductResponse> searchProductCost(Integer productId, ProductRepository productRepository) {
		return productRepository.findById(productId);
	}

	// Headers needed for the api call
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		return headers;
	}

	// Building the external api url
	private String getUrl(ProductApiConfiguration apiConfiguration, Integer productId) {
		StringBuilder url = new StringBuilder();
		url.append(apiConfiguration.getBaseurl());
		url.append(apiConfiguration.getPath());
		url.append(productId);
		url.append(apiConfiguration.getExcludes());
		return url.toString();
	}

}
