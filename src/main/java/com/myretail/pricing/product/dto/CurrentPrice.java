package com.myretail.pricing.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CurrentPrice {

	@PositiveOrZero(message = "Product value must be positive or zero.")
	private Double value;
	@JsonProperty("currency_code")
	@NotBlank(message = "Currency code cannot be null or empty.")
	private String currencyCode;

}
