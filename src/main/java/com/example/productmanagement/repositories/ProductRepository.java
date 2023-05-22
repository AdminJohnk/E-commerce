package com.example.productmanagement.repositories;

import com.example.productmanagement.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
        @Query(value = "{'name': ?0}", exists = true)
        Boolean checkProductName(String productName);
}
