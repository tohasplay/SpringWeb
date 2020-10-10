package com.demo.service.data;

import com.demo.dto.Order;

import java.util.ArrayList;

public interface OrderDataBaseAccess{

    ArrayList<Order> getAllOrders();
    void put(Order data, long id);
    void delete(long id);
    void update(Order data);
    Order getById(long id);
    boolean verifyUser(long id, String password);
    Order getLastOrder();
}
