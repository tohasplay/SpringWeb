package com.demo.businesscore;

import lombok.Data;

public @Data
class Order {

    private long id;
    private String text;
    private float price;

    public Order() {
        super();
    }

    public Order(long id, String text, float price) {
        this.id = id;
        this.text = text;
        this.price = price;
    }
}
