package com.example.knowledge.model.dto;

import lombok.Data;

@Data
public class CarResponse {
    private String username;

    private String name;

    private int price;

    public CarResponse(String username, String name, int price) {
        this.username = username;
        this.name = name;
        this.price = price;
    }
}
