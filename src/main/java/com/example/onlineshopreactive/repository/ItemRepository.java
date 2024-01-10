package com.example.onlineshopreactive.repository;

import com.example.onlineshopreactive.entity.Item;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ItemRepository extends ReactiveMongoRepository<Item,String> ,
        ReactiveQueryByExampleExecutor<Item> {
    @Query("{'description': ?0}")
    Flux<Item> takeIIemsByDescription(String partialName);
    Flux<Item> findByDescriptionContainingIgnoreCase(String partialName);

    Flux<Item> findByNameContainingIgnoreCase(String partialName);
    Flux<Item> findByNameContainingAndDescriptionContainingIgnoreCase(
            String partialName,String partialDesc
    );
}
