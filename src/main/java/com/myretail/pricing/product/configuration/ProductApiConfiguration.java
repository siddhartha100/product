package com.myretail.pricing.product.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("api.product")
@Component
@Getter
@Setter
public class ProductApiConfiguration {
	private String baseurl;
	private String path;
	private String excludes;
	private String httpMethod;
}
