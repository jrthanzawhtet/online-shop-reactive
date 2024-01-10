package com.example.onlineshopreactive.repository;

import com.example.onlineshopreactive.entity.Item;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface ItemByExampleRepository
        extends ReactiveQueryByExampleExecutor<Item> {
}
