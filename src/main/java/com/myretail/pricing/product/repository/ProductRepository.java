package com.myretail.pricing.product.repository;

import com.myretail.pricing.product.dto.ProductResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductResponse, Integer> {

}

