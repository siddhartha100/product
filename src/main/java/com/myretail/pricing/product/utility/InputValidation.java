package com.myretail.pricing.product.utility;

import com.myretail.pricing.product.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class InputValidation {

	public Boolean requestValidation(Integer productId, ProductResponse product) {
		return productId.compareTo(product.get_id()) == 0;
	}

}
