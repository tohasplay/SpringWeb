package com.demo.service.orders;

import lombok.Data;

public @Data
class Order {

    private long id;
    private String text;
    private float price;
    private long customer_id;

    public Order() {
        super();
    }

    public Order(long id, String text, float price, long customer_id) {
        this.id = id;
        this.text = text;
        this.price = price;
        this.customer_id = customer_id;
    }
}
