package com.myretail.pricing.product.controller;

import com.myretail.pricing.product.dto.CurrentPrice;
import com.myretail.pricing.product.dto.ProductResponse;
import com.myretail.pricing.product.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductService productService;

	@Before
	public void initialize() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testProductSearchResponseOk() throws Exception {
		ProductResponse productResponse = getMockProductResponse();
		Mockito.when(this.productService.searchProductById(anyInt())).thenReturn(productResponse);
		String response = "{\"name\":\"testItem\",\"id\":1,\"current_price\":{\"value\":10.01,\"currency_code\":\"USD\"}}";
		this.mvc.perform(get("/v1/product/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(response));
	}

	@Test
	public void testProductSearchResponseNotFound() throws Exception {
		ProductResponse productResponse = new ProductResponse();
		Mockito.when(this.productService.searchProductById(anyInt())).thenReturn(productResponse);
		this.mvc.perform(get("/v1/product/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andExpect(content().string(""));
	}

	@Test
	public void testProductUpdateResponseAccepted() throws Exception {
		ProductResponse request = getMockProductResponse();
		ProductResponse response = new ProductResponse();
		String productResponseString = "{\"name\":\"testItem\",\"id\":1,\"current_price\":{\"value\":10.01,\"currency_code\":\"USD\"}}";
		Mockito.when(this.productService.updateProductInformation(request)).thenReturn(response);
		this.mvc.perform(put("/v1/products/1")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(productResponseString)).andExpect(status().isInternalServerError());
	}

	@Test
	public void testProductUpdateInternalServerError() throws Exception {
		ProductResponse productResponse = getMockProductResponse();
		String productResponseString = "{\"name\":\"testItem\",\"id\":1,\"current_price\":{\"value\":10.01,\"currency_code\":\"USD\"}}";
		Mockito.when(this.productService.updateProductInformation(productResponse)).thenReturn(productResponse);
		this.mvc.perform(put("/v1/products/1")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(productResponseString)).andExpect(status().isCreated()).andExpect(content().string(productResponseString));
	}

	@Test
	public void testProductUpdateBadRequest() throws Exception {
		ProductResponse productResponse = getMockProductResponse();
		String productResponseString = "{\"name\":\"testItem\",\"id\":1,\"current_price\":{\"value\":10.01,\"currency_code\":\"USD\"}}";
		Mockito.when(this.productService.updateProductInformation(productResponse)).thenReturn(productResponse);
		this.mvc.perform(put("/v1/products/{id}", 2)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(productResponseString)).andExpect(status().isBadRequest());
	}

	private ProductResponse getMockProductResponse() {
		ProductResponse productResponse = new ProductResponse();
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setValue(10.01);
		currentPrice.setCurrencyCode("USD");
		productResponse.setCurrentPrice(currentPrice);
		productResponse.setName("testItem");
		productResponse.set_id(1);
		return productResponse;
	}

}
