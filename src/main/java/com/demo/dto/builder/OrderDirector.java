package com.demo.dto.builder;

import com.demo.dto.Order;

public class OrderDirector {
    private OrderBuilder orderBuilder;

    public void setOrderBuilder(OrderBuilder orderBuilder) {
        this.orderBuilder = orderBuilder;
    }

    public Order getOrder() {
        return orderBuilder.getOrder();
    }

    public void constructOrder() {
        orderBuilder.createNewOrder();
        orderBuilder.buildId();
        orderBuilder.buildText();
        orderBuilder.buildPrice();
    }
}
