package com.myretail.pricing.product.dto.pdp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	@JsonProperty("available_to_promise_network")
	private AvailableToPromiseNetwrok availableToPromiseNetwork;
	private Item item;
}

