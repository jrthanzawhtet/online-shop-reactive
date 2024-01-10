package com.example.onlineshopreactive.service;

import com.example.onlineshopreactive.entity.Cart;
import com.example.onlineshopreactive.entity.CartItem;
import com.example.onlineshopreactive.repository.CartRepository;
import com.example.onlineshopreactive.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public Mono<Cart> addToCart(String cartId, String id) {
        return cartRepository.findById(cartId)
                .defaultIfEmpty(new Cart(cartId))
                .flatMap(cart -> cart.getCartItems()
                        .stream()
                        .filter(cartItem -> cartItem.getItem()
                                .getId().equals(id))
                        .findAny()
                        .map(cartItem -> {
                            cartItem.increment();
                            return Mono.just(cart);
                        })
                        .orElseGet(() -> itemRepository.findById(id)
                                .map(CartItem::new)
                                .doOnNext(cartItem ->
                                        cart.getCartItems().add(cartItem)
                                )
                                .map(cartItem -> cart)
                        )
                ).flatMap(cartRepository::save);
    }

}













