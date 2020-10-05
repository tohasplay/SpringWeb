package com.demo.businesscore;

public class OrderCreator {
    public Order createOrder(String text) {
        Order order = new Order(0, text, priceGenerator());
        return order;
    }

    private float priceGenerator() {
        return Math.round(100f * (float) (100 + Math.random() * 400)) / 100f;
    }

}
