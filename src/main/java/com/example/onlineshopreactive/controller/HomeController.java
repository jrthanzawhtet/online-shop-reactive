package com.example.onlineshopreactive.controller;

import com.example.onlineshopreactive.entity.Cart;
import com.example.onlineshopreactive.entity.CartItem;
import com.example.onlineshopreactive.repository.CartRepository;
import com.example.onlineshopreactive.repository.ItemRepository;
import com.example.onlineshopreactive.service.CartService;
import com.example.onlineshopreactive.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final ItemService itemService;
    @PostMapping("/add/{id}")
    public  Mono<String>  addToCart(@PathVariable String id){
        return cartService.addToCart("My Cart",id)
                .thenReturn("redirect:/home");
    }

    @GetMapping("/index")
    public Mono<Rendering> hello(){

        return Mono.just(Rendering.view("index")
                .modelAttribute("tagline","Helloworld").build());
    }
    @GetMapping({"/","/home"})
    public Mono<Rendering> home(){
        itemService.searchByExample(null,"Electronics",false)
                .subscribe(item -> System.out.println(item));

        return Mono.just(Rendering.view("home")
                .modelAttribute("items",itemRepository.findAll())
                .modelAttribute("cart",cartRepository.findById("My Cart")
                        .defaultIfEmpty(new Cart("My Cart"))).build()

        );
    }





}
