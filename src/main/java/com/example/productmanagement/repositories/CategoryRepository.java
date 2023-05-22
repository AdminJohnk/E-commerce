package com.example.productmanagement.repositories;

import com.example.productmanagement.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    @Query(value = "{'code': ?0}", exists = true)
    Boolean checkCategoryCode(String categoryCode);
}
