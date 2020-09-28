package com.demo.businesscore.order;

import com.demo.businesscore.customer.Customer;

public class OrderCreator {
    private static long id = 0;

    public Order createOrder(Customer customer, String text){
        Order order = new Order(id++, text, priceGenerator());
        customer.getOrders().add(order);
        return order;
    }

    private float priceGenerator(){
        return Math.round(100f * (float)(100 + Math.random() * 400)) / 100f;
    }
}
