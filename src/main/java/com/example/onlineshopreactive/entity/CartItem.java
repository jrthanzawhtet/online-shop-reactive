package com.example.onlineshopreactive.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CartItem {
    private Item item;
    private int quantity;

    public CartItem(Item item) {
        this.item = item;
        quantity = 1;
    }
    public int increment(){
        return ++quantity;
    }

    public CartItem() {
    }
}
