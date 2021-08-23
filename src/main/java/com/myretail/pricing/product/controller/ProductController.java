package com.myretail.pricing.product.controller;

import com.myretail.pricing.product.dto.ProductResponse;
import com.myretail.pricing.product.exception.InputValidationException;
import com.myretail.pricing.product.service.ProductService;
import com.myretail.pricing.product.utility.InputValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("v1")
@Validated
public class ProductController {

	private static final Logger LOGGER = LogManager.getLogger(ProductController.class);
	private final ProductService productService;
	private final InputValidation inputValidation;

	public ProductController(ProductService productService, InputValidation inputValidation) {

		this.productService = productService;
		this.inputValidation = inputValidation;
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<ProductResponse> searchProductById(@PathVariable("id") @NotNull @Positive Integer productId) throws ExecutionException, InterruptedException {

		// Call service layer
		long startTime = System.nanoTime();
		ProductResponse response = productService.searchProductById(productId);
		long responseTime = (System.nanoTime() - startTime) / 1000000;
		LOGGER.info("Product search service response time for product {} : {} milli seconds", productId, responseTime);

		// Return response
		return response.get_id() != null ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> updateProductById(
			@PathVariable("id") @NotNull @Positive Integer productId, @Valid @RequestBody ProductResponse product) {

		// Input validation
		if (!inputValidation.requestValidation(productId, product)) {
			throw new InputValidationException(productId);
		}

		// Call service layer
		long startTime = System.nanoTime();
		ProductResponse response = productService.updateProductInformation(product);
		long responseTime = (System.nanoTime() - startTime) / 1000000;
		LOGGER.info("Product update service response time for product {} : {} milli seconds", productId, responseTime);

		// Return response
		return response.get_id() != null ? new ResponseEntity<>(response, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
