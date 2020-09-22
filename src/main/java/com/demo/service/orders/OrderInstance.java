package com.demo.service.orders;

import java.util.ArrayList;

public interface OrderInstance {
    ArrayList<Order> getAllOrders();
    Order getOrderById(long id);
    Order addOrder(Order order);
    Order updateOrder(Order order, long id);
    void deleteOrder(long id);
}
