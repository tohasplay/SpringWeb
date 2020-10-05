package com.demo.service.presentor;

import com.demo.businesscore.Order;

import java.util.ArrayList;

public interface OrderUndefinedPresentor {

    ArrayList<Order> getAll();
    Order add(Order data, long id, String password);
    Order get(long id);
    Order update(Order data);
    void delete(long id);
}
