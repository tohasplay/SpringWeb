package com.demo.dto.builder;

import com.demo.dto.Order;

public abstract class OrderBuilder {

    protected Order order;

    public Order getOrder() {
        return order;
    }

    public void createNewOrder(){
        order = new Order();
    }

    public abstract void buildId();
    public abstract void buildText();
    public abstract void buildPrice();
}
