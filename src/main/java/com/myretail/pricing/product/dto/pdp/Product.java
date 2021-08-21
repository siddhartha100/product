package com.myretail.pricing.product.dto.pdp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	@JsonProperty("available_to_promise_network")
	private AvailableToPromiseNetwork availableToPromiseNetwork;
	private Item item;
}

