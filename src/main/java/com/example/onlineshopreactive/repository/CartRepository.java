package com.example.onlineshopreactive.repository;

import com.example.onlineshopreactive.entity.Cart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends ReactiveMongoRepository<Cart,String> {
}
