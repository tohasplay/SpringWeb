package com.demo.service;

public class Order {
    private long id;
    private String text;
    private float price;
    private long customer_id;

    public Order() {
        super();
    }

    public Order(String text, float price, long customer_id) {
        this.text = text;
        this.price = price;
        this.customer_id = customer_id;
    }

    public Order(long id, String text, float price, long customer_id) {
        this.id = id;
        this.text = text;
        this.price = price;
        this.customer_id = customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public float getPrice() {
        return price;
    }

    public long getCustomer_id() {
        return customer_id;
    }
}
