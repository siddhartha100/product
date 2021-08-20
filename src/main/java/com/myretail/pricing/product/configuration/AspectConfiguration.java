package com.myretail.pricing.product.configuration;

import com.myretail.pricing.product.dto.ProductResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfiguration {

	private static final Logger LOGGER = LogManager.getLogger(AspectConfiguration.class);

	@Before(value = "execution(* com.myretail.pricing.product.service.ProductService.*(..)) and args(productId)")
	public void beforeAdvice(JoinPoint joinPoint, Integer productId) {
		LOGGER.info("Before method: searchProductById {} Product ID: {} ", joinPoint.getSignature(), productId);
	}

	@After(value = "execution(* com.myretail.pricing.product.service.ProductService.*(..)) and args(productId)")
	public void afterAdvice(JoinPoint joinPoint, Integer productId) {
		LOGGER.info("After method: searchProductById {} Product ID: {} ", joinPoint.getSignature(), productId);
	}

	@Before(value = "execution(* com.myretail.pricing.product.service.ProductService.*(..)) and args(productResponse)")
	public void beforeAdvice(JoinPoint joinPoint, ProductResponse productResponse) {
		LOGGER.info("Before method: updateProductCost {} Product: {} ", joinPoint.getSignature(), productResponse);
	}

	@After(value = "execution(* com.myretail.pricing.product.service.ProductService.*(..)) and args(productResponse)")
	public void afterAdvice(JoinPoint joinPoint, ProductResponse productResponse) {
		LOGGER.info("After method: updateProductCost {} Product: {} ", joinPoint.getSignature(), productResponse);
	}

}
