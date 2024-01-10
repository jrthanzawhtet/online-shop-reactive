package com.example.onlineshopreactive.service;

import com.example.onlineshopreactive.entity.Item;
import com.example.onlineshopreactive.repository.ItemByExampleRepository;
import com.example.onlineshopreactive.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;


    public Flux<Item> searchByExample(String name,String description,
                                      boolean useAnd){
        Item item=new Item(name,0.0,description);
        ExampleMatcher matcher=(
                useAnd? ExampleMatcher.matchingAll()
                        :ExampleMatcher.matchingAny()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                        .withIgnoreCase()
                        .withIgnoreCase("price")
                );
        Example<Item> prob=Example.of(item,matcher);
        return itemRepository.findAll(prob);
    }


    public Flux<Item> search(String partialName,String partialDescription,
                             boolean useAnd){
        if(partialName != null){
            if(partialDescription !=null){
                if(useAnd){
                    return itemRepository
                            .findByNameContainingAndDescriptionContainingIgnoreCase(
                                    partialName,partialDescription
                            );
                }
                else {
                    return itemRepository
                            .findByNameContainingAndDescriptionContainingIgnoreCase(
                                    partialName,partialDescription
                            );
                }
            }
            else{
                return itemRepository.findByNameContainingIgnoreCase(partialName);
            }
        }
        else{
            if(partialDescription !=null){
                return itemRepository
                        .findByDescriptionContainingIgnoreCase(partialDescription);
            }
            else {
                return itemRepository.findAll();
            }
        }
    }
}












