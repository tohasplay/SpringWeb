package com.demo.service.orders;

import com.demo.service.orders.Order;

import java.util.ArrayList;

public interface OrderInstance {
    public ArrayList<Order> getAllOrders();
    public Order getOrderById(long id);
    public Order addOrder(Order order);
    public Order updateOrder(Order order, long id);
    public void deleteOrder(long id);
}
