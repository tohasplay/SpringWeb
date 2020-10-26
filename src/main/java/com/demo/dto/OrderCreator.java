package com.demo.dto;

import com.demo.dto.builder.AutoPricedOrder;

/**
 * use builder instead
 * {@link com.demo.dto.builder.OrderBuilder}
 * {@link AutoPricedOrder}
 * {@link com.demo.dto.builder.OrderDirector}
 */
@Deprecated
//DONE: pattern builder, gang of for (read)
public class OrderCreator {
    public Order createOrder(String text) {
        return new Order(0, text, priceGenerator());
    }

    private float priceGenerator() {
        return Math.round(100f * (float) (100 + Math.random() * 400)) / 100f;
    }

}
