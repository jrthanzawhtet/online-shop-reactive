package com.example.onlineshopreactive;

import com.example.onlineshopreactive.entity.Item;
import com.example.onlineshopreactive.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootApplication
@RequiredArgsConstructor
public class OnlineShopReactiveApplication {
    private final ItemRepository repository;
   @Bean @Profile("test")
    public CommandLineRunner runner(){
        return r ->{
            repository.insert(new Item("Electric Voilin",150.99,"Electronics")).subscribe();
            repository.insert(new Item("Guitar",700,"Electronics")).subscribe();
            repository.insert(new Item("HandBag Parada",700,"Accessories")).subscribe();
            repository.insert(new Item("Pizza",700,"Food")).subscribe();
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineShopReactiveApplication.class, args);
    }

}
