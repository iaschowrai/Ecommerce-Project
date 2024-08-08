package com.iaschowrai.productservice.repository;

import com.iaschowrai.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
