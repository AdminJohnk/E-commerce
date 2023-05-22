package com.example.productmanagement.repositories;

import com.example.productmanagement.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
        @Query(value = "{'email': ?0}", exists = true)
        Boolean checkUserEmail(String Email);

        @Query(value = "{'email': ?0}")
        Optional<User> findByEmail(String email);
}
