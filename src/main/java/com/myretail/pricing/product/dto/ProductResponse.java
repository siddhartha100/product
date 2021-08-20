package com.myretail.pricing.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class ProductResponse {
	@Id
	@JsonProperty("id")
	@NotNull(message ="Product key cannot be null. Key must be an Integer.")
	private Integer _id;
	@NotBlank(message ="Product name cannot be null or empty.")
	private String name;
	@JsonProperty("current_price")
	@NotNull(message ="Price information cannot be null.")
	private CurrentPrice currentPrice;
}
