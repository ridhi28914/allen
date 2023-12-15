package com.example.allen.service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seller {

    private String sellerId;
    private String name;

    public Seller(String sellerId, String name) {
        this.sellerId = sellerId;
        this.name = name;
    }
}
