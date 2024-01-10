package com.example.onlineshopreactive.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
public class Item {
    @Id
    private String id;
    private String name;
    private double price;
    private String description;

    private Item(){}

    public Item(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
